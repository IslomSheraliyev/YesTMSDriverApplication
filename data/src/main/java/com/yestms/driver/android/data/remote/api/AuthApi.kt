package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.AuthUrl
import com.yestms.driver.android.data.remote.request.auth.AuthLoginDriverRequest
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverResponse
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET(AuthUrl.CHECK)
    suspend fun check(): AuthCheckResponse?

    @POST(AuthUrl.LOGIN_DRIVER)
    suspend fun loginDriver(
        @Body body: AuthLoginDriverRequest
    ): AuthLoginDriverResponse?
}