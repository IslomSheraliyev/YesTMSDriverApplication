package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName

data class DispatcherRemoteModel(
    val id: Int?,
    @SerializedName("dispatcher_assigned")
    val dispatcherAssigned: DispatcherAssignedRemoteModel?
)