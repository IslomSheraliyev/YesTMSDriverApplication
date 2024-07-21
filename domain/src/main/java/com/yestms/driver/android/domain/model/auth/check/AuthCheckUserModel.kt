package com.yestms.driver.android.domain.model.auth.check

import com.yestms.driver.android.domain.model.loads.DispatcherModel

data class AuthCheckUserModel(
    val fullName: String,
    val isOnDuty: Boolean,
    val userRole: AuthCheckUserRoleModel,
    val dispatchers:List<DispatcherModel>
)