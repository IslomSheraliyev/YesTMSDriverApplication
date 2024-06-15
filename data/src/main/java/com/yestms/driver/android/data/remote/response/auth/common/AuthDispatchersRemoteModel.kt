package com.yestms.driver.android.data.remote.response.auth.login_driver

import com.google.gson.annotations.SerializedName

data class AuthLoginDriverDispatchersRemoteModel(
    val id: Int?,
    @SerializedName("dispatcher_assigned")
    val dispatcherAssigned: AuthLoginDriverDispatcherRemoteModel?
)