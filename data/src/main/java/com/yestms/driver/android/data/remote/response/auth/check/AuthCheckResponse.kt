package com.yestms.driver.android.data.remote.response.auth

import com.yestms.driver.android.data.remote.response.common.UserRemoteModel

data class AuthCheckResponse(
    val token: String?,
    val user: UserRemoteModel?
)