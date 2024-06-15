package com.yestms.driver.android.data.remote.response.auth.login_driver

data class AuthLoginDriverResponse(
    val token: String?,
    val user: AuthLoginDriverUserRemoteModel?
)