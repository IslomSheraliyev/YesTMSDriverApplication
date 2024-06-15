package com.yestms.driver.android.data.remote.response.auth

import com.yestms.driver.android.data.remote.response.auth.login.AuthLoginDriverUserRemoteModel

data class AuthLoginDriverResponse(
    val token: String?,
    val user: AuthLoginDriverUserRemoteModel?
)