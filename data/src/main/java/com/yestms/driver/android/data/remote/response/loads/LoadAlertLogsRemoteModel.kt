package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName

data class LoadAlertLogsRemoteModel(
    val id: Int?,
    val createdAt: String?,
    @SerializedName("load_alert_status")
    val loadAlertStatus: LoadAlertLogRemoteMode
)
