package com.yestms.driver.android.domain.usecase.auth

import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers

class AuthCheckUseCase(
    private val repository: AuthRepository
) : UseCase<AuthCheckModel>(Dispatchers.IO) {
    override suspend fun execute(): AuthCheckModel {
        return repository.check()
    }
}