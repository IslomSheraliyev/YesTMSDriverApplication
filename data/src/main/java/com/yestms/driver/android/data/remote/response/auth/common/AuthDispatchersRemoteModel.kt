package com.yestms.driver.android.data.remote.response.auth.common

import com.google.gson.annotations.SerializedName

data class AuthDispatchersRemoteModel(
    val id: Int?,
    @SerializedName("dispatcher_assigned")
    val dispatcherAssigned: AuthDispatcherRemoteModel?
)