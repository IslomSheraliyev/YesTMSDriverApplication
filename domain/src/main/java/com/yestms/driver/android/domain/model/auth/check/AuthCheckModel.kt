package com.yestms.driver.android.domain.model.auth.check

data class AuthCheckModel(
    val token: String,
    val user: AuthCheckUserModel
)