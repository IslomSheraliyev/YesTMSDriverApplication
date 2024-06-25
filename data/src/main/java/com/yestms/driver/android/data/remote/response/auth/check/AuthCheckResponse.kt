package com.yestms.driver.android.data.remote.response.auth.check

data class AuthCheckResponse(
    val token: String?,
    val user: AuthCheckUserRemoteModel?
)