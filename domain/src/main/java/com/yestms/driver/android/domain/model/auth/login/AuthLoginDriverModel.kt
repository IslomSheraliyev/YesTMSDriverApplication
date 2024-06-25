package com.yestms.driver.android.domain.model.auth.login

data class AuthLoginDriverModel(
    val token: String,
    val user: AuthLoginDriverUserModel
)