package com.yestms.driver.android.ui.screens.main

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.enums.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.loads.UpdateLoadStatusUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketChangeForDashboardUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketConnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketSendNoticeUseCase
import com.yestms.driver.android.domain.usecase.user.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authCheckUseCase: AuthCheckUseCase,
    private val authLoginDriverUseCase: AuthLoginDriverUseCase,
    private val getUnreadCountUseCase: GetUnreadCountUseCase,
    private val updateUseCase: UpdateUseCase,
    private val updateLoadStatusUseCase: UpdateLoadStatusUseCase,
    private val socketChangeForDashboardUseCase: SocketChangeForDashboardUseCase,
    private val socketSendNoticeUseCase: SocketSendNoticeUseCase,
    private val socketConnectUseCase: SocketConnectUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()


    fun check() = vmScope.launch {
        _isRefreshing.emit(true)
        authCheckUseCase()
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        tokenStatus = AuthCheckTokenStatus.VALID,
                        isOnDuty = result.user.isOnDuty
                    )
                }
                AppPreferences.accessToken = result.token
                AppPreferences.authCheckModel = result
                AppPreferences.fullName = result.user.fullName
                AppPreferences.currentRoleId = result.user.userRole.id
                socketConnectUseCase()
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        tokenStatus = AuthCheckTokenStatus.INVALID
                    )
                }
                errorProcess(error)
            }
        _isRefreshing.emit(false)
    }

    fun loginDriver(externalId: String) = vmScope.launch {
        _isRefreshing.emit(true)
        _uiState.update { it.copy(tokenStatus = AuthCheckTokenStatus.IDLE) }
        if (externalId.isEmpty()) {
            _uiState.update { it.copy(externalIdStatus = AuthLoginDriverExternalIdStatus.IDLE) }
            _isRefreshing.emit(false)
        } else
            authLoginDriverUseCase(externalId).onSuccess { result ->
                _uiState.update { it.copy(externalIdStatus = AuthLoginDriverExternalIdStatus.VALID) }
                AppPreferences.accessToken = result.token
                AppPreferences.currentUserId = result.user.id
                check()
            }.onFailure { error ->
                _isRefreshing.emit(false)
                _uiState.update { it.copy(externalIdStatus = AuthLoginDriverExternalIdStatus.INVALID) }
                errorProcess(error)
            }
    }

    fun getUnreadCount() = vmScope.launch {
        getUnreadCountUseCase().onSuccess { count ->
            _uiState.update { it.copy(unreadCount = count) }
        }.onFailure(::errorProcess)
    }

    fun update(isOnDuty: Boolean) = vmScope.launch {
        updateUseCase(AppPreferences.currentUserId, isOnDuty)
            .onSuccess {
                _uiState.update { it.copy(isOnDuty = !uiState.value.isOnDuty) }

//                socketConnectUseCase().onSuccess {
                val dispatchers = AppPreferences.authCheckModel?.user?.dispatchers
                    ?.map { it.id }
                    ?.toMutableList()

                socketSendNoticeUseCase(
                    parameter1 = dispatchers.orEmpty(),
                    parameter2 = "Status of driver ${AppPreferences.fullName} changed on ${if (isOnDuty) "On duty" else "Off duty"} by ${AppPreferences.fullName}",
                    parameter3 = "info"
                )
                dispatchers?.add(AppPreferences.currentUserId)
                socketChangeForDashboardUseCase(dispatchers.orEmpty())
//                }
            }.onFailure(::errorProcess)
    }

    fun updateLoadStatusToSeen(loadId: Int) = vmScope.launch {
        updateLoadStatusUseCase(loadId, 2)
            .onFailure(::errorProcess)
    }

    fun resetTokenStatus() = vmScope.launch {
        _uiState.update { it.copy(tokenStatus = AuthCheckTokenStatus.INVALID) }
    }

    fun resetExternalIdStatus() = vmScope.launch {
        _uiState.update { it.copy(externalIdStatus = AuthLoginDriverExternalIdStatus.IDLE) }
    }
}

data class MainUIState(
    val tokenStatus: AuthCheckTokenStatus = AuthCheckTokenStatus.IDLE,
    val externalIdStatus: AuthLoginDriverExternalIdStatus = AuthLoginDriverExternalIdStatus.IDLE,
    val unreadCount: Int = 0,
    val isOnDuty: Boolean = false
)