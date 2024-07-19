package com.yestms.driver.android.data.remote.response.auth.check

data class AuthCheckUserRemoteModel(
    val fullName: String?,
    val isOnDuty: Boolean?,
    val user_role: AuthCheckUserRoleRemoteModel
)