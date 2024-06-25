package com.yestms.driver.android.data.remote.request.user

data class UpdateUserStatusRequest(
    val id: Int,
    val isOnDuty: Boolean,
)