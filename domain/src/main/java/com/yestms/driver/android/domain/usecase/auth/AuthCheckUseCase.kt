package com.yestms.driver.android.domain.usecase.auth

import com.yestms.driver.android.domain.global.UseCaseWithoutParams
import com.yestms.driver.android.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AuthCheckUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCaseWithoutParams(Dispatchers.IO) {
    override suspend fun execute() {
        return repository.check()
    }
}