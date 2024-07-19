package com.yestms.driver.android.domain.model.auth.check

data class AuthCheckUserModel(
    val fullName: String,
    val isOnDuty: Boolean,
    val userRole: AuthCheckUserRoleModel
)