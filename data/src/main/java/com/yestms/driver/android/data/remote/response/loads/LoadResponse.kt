package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName

data class LoadResponse(
    val id: Int?,
    val loadId: String?,
    val activationLink: String?,
    val rate: Long?,
    val mileage: Int?,
    val commodity: String?,
    val weight: String?,
    val height: String?,
    val length: String?,
    val notes: String?,
    val pickUpNote: String?,
    val pickUpLocation: String?,
    val pickUpPolitical: String?,
    val deliveryNote: String?,
    val deliveryLocation: String?,
    val deliveryPolitical: String?,
    @SerializedName("load_status")
    val loadStatus: LoadStatusRemoteModel?,
    @SerializedName("load_alerts_logs")
    val loadAlertsLogs: List<LoadAlertLogsRemoteModel>,
    @SerializedName("load_status_logs")
    val loadStatusLogs: List<LoadStatusLogsRemoteItem>
)