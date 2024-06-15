package com.yestms.driver.android.ui.screens.main

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.enums.auth.AuthCheckTokenStatus
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authCheckUseCase: AuthCheckUseCase
) : BaseViewModel() {

    private val _tokenStatus = MutableStateFlow(AuthCheckTokenStatus.IDLE)
    val tokenStatus = _tokenStatus.asStateFlow()

    fun check() = vmScope.launch {
        _isRefreshing.emit(true)

        authCheckUseCase().onSuccess {
            _tokenStatus.emit(AuthCheckTokenStatus.VALID)
        }.onFailure {
            _tokenStatus.emit(AuthCheckTokenStatus.INVALID)
        }

        _isRefreshing.emit(false)
    }
}