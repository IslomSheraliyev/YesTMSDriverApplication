package com.yestms.driver.android.data.remote.response.loads

import com.google.gson.annotations.SerializedName

data class LoadResponse(
    val id: Int?,
    val loadId: String?,
    val activationLink: String?,
    val rate: Int?,
    val mileage: Int?,
    val pickUpNote: String?,
    val pickUpLocation: String?,
    val pickUpPolitical: String?,
    val deliveryLocation: String?,
    val deliveryPolitical: String?,
    @SerializedName("load_status")
    val loadStatus: LoadStatusRemoteModel?
)