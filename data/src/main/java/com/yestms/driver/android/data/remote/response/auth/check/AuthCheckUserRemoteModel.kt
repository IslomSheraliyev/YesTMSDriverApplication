package com.yestms.driver.android.data.remote.response.auth.check

import com.yestms.driver.android.data.remote.response.loads.DispatcherRemoteModel

data class AuthCheckUserRemoteModel(
    val fullName: String?,
    val isOnDuty: Boolean?,
    val user_role: AuthCheckUserRoleRemoteModel,
    val dispatchers: List<DispatcherRemoteModel>?
)