package com.yestms.driver.android.ui.screens.login

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.enums.auth.AuthLoginDriverExternalIdStatus
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authLoginDriverUseCase: AuthLoginDriverUseCase
) : BaseViewModel() {

    private val _isUserAuthLoginDriverExternalIdValidStatus =
        MutableStateFlow(AuthLoginDriverExternalIdStatus.IDLE)
    val isUserExternalIdValid = _isUserAuthLoginDriverExternalIdValidStatus.asStateFlow()

    fun loginDriver(externalId: String) = vmScope.launch {

        if (externalId.isEmpty())
            _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.IDLE)
        else
            authLoginDriverUseCase(externalId).onSuccess { result ->
                _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.VALID)
                AppPreferences.accessToken = result.token
            }.onFailure {
                _isUserAuthLoginDriverExternalIdValidStatus.emit(AuthLoginDriverExternalIdStatus.INVALID)
            }
    }
}