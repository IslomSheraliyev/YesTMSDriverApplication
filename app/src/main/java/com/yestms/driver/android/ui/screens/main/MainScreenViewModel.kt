package com.yestms.driver.android.ui.screens.main

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.enums.auth.AuthCheckTokenStatus
import com.yestms.driver.android.data.enums.auth.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authCheckUseCase: AuthCheckUseCase,
    private val authLoginDriverUseCase: AuthLoginDriverUseCase,
    private val getUnreadCountUseCount: GetUnreadCountUseCount
) : BaseViewModel() {

    private val _tokenStatus = MutableStateFlow(AuthCheckTokenStatus.IDLE)
    val tokenStatus = _tokenStatus.asStateFlow()

    private val _isUserAuthLoginDriverExternalIdValidStatus =
        MutableStateFlow(AuthLoginDriverExternalIdStatus.IDLE)
    val isUserExternalIdValid = _isUserAuthLoginDriverExternalIdValidStatus.asStateFlow()

    private val _unreadCount = MutableStateFlow(0)
    val unreadCount = _unreadCount.asStateFlow()

    fun check() = vmScope.launch {
        _isRefreshing.emit(true)

        authCheckUseCase()
            .onSuccess {
                _tokenStatus.emit(AuthCheckTokenStatus.VALID)
            }.onFailure {
                _tokenStatus.emit(AuthCheckTokenStatus.INVALID)
            }

        _isRefreshing.emit(false)
    }

    fun loginDriver(externalId: String) = vmScope.launch {
        _tokenStatus.emit(AuthCheckTokenStatus.IDLE)

        _isRefreshing.emit(true)

        if (externalId.isEmpty()) {
            _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.IDLE)
            _isRefreshing.emit(false)
        } else
            authLoginDriverUseCase(externalId).onSuccess { result ->
                _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.VALID)
                AppPreferences.accessToken = result.token
                check()
            }.onFailure {
                _isRefreshing.emit(false)
                _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.INVALID)
            }
    }

    fun getUnreadCount() = vmScope.launch {
        getUnreadCountUseCount().onSuccess { count ->
            _unreadCount.emit(count)
        }.onFailure(::errorProcess)
    }
}