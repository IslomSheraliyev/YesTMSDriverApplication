package com.yestms.driver.android.data.remote.response.loads.get

import com.google.gson.annotations.SerializedName


data class LoadRemoteModel(
    val id: Int?,
    val rate: Int?,
    val mileage: Int?,
    val pickUpLocation: String?,
    val pickUpPolitical: String?,
    val deliveryLocation: String?,
    val deliveryPolitical: String?,
    @SerializedName("load_status")
    val loadStatus: LoadStatusRemoteModel?,
)