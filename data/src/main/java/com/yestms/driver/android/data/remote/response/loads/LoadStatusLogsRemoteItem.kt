package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName


data class LoadStatusLogsRemoteItem(
    val createdAt: String,
    @SerializedName("load_status")
    val loadStatusLogsItemLoadStatus: LoadStatusLogsRemoteItemLoadStatus
)
