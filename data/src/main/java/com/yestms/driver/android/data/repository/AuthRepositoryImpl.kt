package com.yestms.driver.android.data.repository

import com.yestms.driver.android.data.mapper.auth.CheckMapper.checkMapper
import com.yestms.driver.android.data.mapper.auth.LoginDriverMapper
import com.yestms.driver.android.data.remote.api.AuthApi
import com.yestms.driver.android.data.remote.request.auth.AuthLoginDriverRequest
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverModel
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
        return api.check().let(checkMapper)
    }
}