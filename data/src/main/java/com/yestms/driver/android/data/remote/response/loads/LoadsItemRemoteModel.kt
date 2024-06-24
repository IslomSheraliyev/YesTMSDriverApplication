package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName


data class LoadsItemRemoteModel(
    val id: Int?,
    val loadId: String?,
    val rate: Long?,
    val mileage: Int?,
    val pickUpLocation: String?,
    val pickUpPolitical: String?,
    val deliveryLocation: String?,
    val deliveryPolitical: String?,
    @SerializedName("load_status")
    val loadStatus: LoadStatusRemoteModel?,
)