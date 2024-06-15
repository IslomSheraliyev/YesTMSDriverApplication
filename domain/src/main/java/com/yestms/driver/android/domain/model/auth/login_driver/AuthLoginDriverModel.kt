package com.yestms.driver.android.domain.model.auth

data class AuthLoginDriverModel(
    val token: String?,
    val user: AuthLoginDriverUserModel?
)