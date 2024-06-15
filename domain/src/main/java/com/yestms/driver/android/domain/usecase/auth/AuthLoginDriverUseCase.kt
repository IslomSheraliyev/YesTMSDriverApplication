package com.yestms.driver.android.domain.usecase.auth

import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverModel
import com.yestms.driver.android.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers

class AuthLoginDriverUseCase(
    private val repository: AuthRepository
) : UseCaseWithParams<String, AuthLoginDriverModel>(Dispatchers.IO) {
    override suspend fun execute(parameter: String): AuthLoginDriverModel {
        return repository.loginDriver(parameter)
    }
}