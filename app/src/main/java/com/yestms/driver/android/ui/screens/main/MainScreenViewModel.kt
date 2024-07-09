package com.yestms.driver.android.ui.screens.main

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.enums.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.loads.UpdateLoadStatusUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCase
import com.yestms.driver.android.domain.usecase.user.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authCheckUseCase: AuthCheckUseCase,
    private val authLoginDriverUseCase: AuthLoginDriverUseCase,
    private val getUnreadCountUseCase: GetUnreadCountUseCase,
    private val updateUseCase: UpdateUseCase,
    private val updateLoadStatusUseCase: UpdateLoadStatusUseCase
) : BaseViewModel() {

    private val _tokenStatus = MutableStateFlow(AuthCheckTokenStatus.IDLE)
    val tokenStatus = _tokenStatus.asStateFlow()

    private val _externalIdStatus =
        MutableStateFlow(AuthLoginDriverExternalIdStatus.IDLE)
    val externalIdStatus = _externalIdStatus.asStateFlow()

    private val _unreadCount = MutableStateFlow(0)
    val unreadCount = _unreadCount.asStateFlow()

    private val _isOnDuty = MutableStateFlow(false)
    val isOnDuty = _isOnDuty.asStateFlow()

    fun check() = vmScope.launch {
        _isRefreshing.emit(true)

        authCheckUseCase()
            .onSuccess { result ->
                _tokenStatus.emit(AuthCheckTokenStatus.VALID)
                _isOnDuty.emit(result.user.isOnDuty)
                AppPreferences.accessToken = result.token
            }.onFailure {
                _tokenStatus.emit(AuthCheckTokenStatus.INVALID)
                errorProcess(it)
            }

        _isRefreshing.emit(false)
    }

    fun loginDriver(externalId: String) = vmScope.launch {
        _tokenStatus.emit(AuthCheckTokenStatus.IDLE)

        _isRefreshing.emit(true)

        if (externalId.isEmpty()) {
            _externalIdStatus.emit(AuthLoginDriverExternalIdStatus.IDLE)
            _isRefreshing.emit(false)
        } else
            authLoginDriverUseCase(externalId).onSuccess { result ->
                _externalIdStatus.emit(AuthLoginDriverExternalIdStatus.VALID)
                AppPreferences.accessToken = result.token
                AppPreferences.currentUserId = result.user.id
                check()
            }.onFailure {
                _isRefreshing.emit(false)
                _externalIdStatus.emit(AuthLoginDriverExternalIdStatus.INVALID)
                errorProcess(it)
            }
    }

    fun getUnreadCount() = vmScope.launch {
        getUnreadCountUseCase().onSuccess { count ->
            _unreadCount.emit(count)
        }.onFailure(::errorProcess)
    }

    fun update(isOnDuty: Boolean) = vmScope.launch {
        updateUseCase(AppPreferences.currentUserId, isOnDuty)
            .onSuccess {
                _isOnDuty.emit(!_isOnDuty.value)
            }.onFailure(::errorProcess)
    }

    fun updateLoadStatus(loadId: Int) = vmScope.launch {
        updateLoadStatusUseCase(loadId, 2)
            .onFailure(::errorProcess)
    }

    fun resetTokenStatus() = vmScope.launch { _tokenStatus.emit(AuthCheckTokenStatus.IDLE) }

    fun resetExternalIdStatus() =
        vmScope.launch { _externalIdStatus.emit(AuthLoginDriverExternalIdStatus.IDLE) }

}