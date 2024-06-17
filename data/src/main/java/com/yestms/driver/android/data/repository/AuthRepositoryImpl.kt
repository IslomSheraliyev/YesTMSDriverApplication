package com.yestms.driver.android.data.repository

import com.yestms.driver.android.data.mapper.LoginDriverMapper
import com.yestms.driver.android.data.remote.api.AuthApi
import com.yestms.driver.android.data.remote.request.auth.AuthLoginDriverRequest
import com.yestms.driver.android.domain.model.auth.AuthLoginDriverModel
import com.yestms.driver.android.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun loginDriver(externalId: String): AuthLoginDriverModel {
        return api.loginDriver(AuthLoginDriverRequest(externalId))
            .let(LoginDriverMapper.loginDriverMapper)
    }

    override suspend fun check() {
        return api.check()
    }
}