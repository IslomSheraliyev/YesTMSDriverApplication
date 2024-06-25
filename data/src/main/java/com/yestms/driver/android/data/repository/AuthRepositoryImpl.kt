package com.yestms.driver.android.data.repository

import com.yestms.driver.android.data.mapper.CheckMapper
import com.yestms.driver.android.data.mapper.LoginDriverMapper
import com.yestms.driver.android.data.mapper.orFalse
import com.yestms.driver.android.data.remote.api.AuthApi
import com.yestms.driver.android.data.remote.request.auth.AuthLoginDriverRequest
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.login.AuthLoginDriverModel
import com.yestms.driver.android.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun loginDriver(externalId: String): AuthLoginDriverModel {
        return api.loginDriver(AuthLoginDriverRequest(externalId))
            .let(LoginDriverMapper.loginDriverMapper)
    }

    override suspend fun check(): AuthCheckModel {
        return api.check().let(CheckMapper.checkMapper)
    }
}