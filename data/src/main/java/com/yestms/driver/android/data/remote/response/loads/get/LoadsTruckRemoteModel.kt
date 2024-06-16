package com.yestms.driver.android.data.remote.response.loads.get

import com.google.gson.annotations.SerializedName

data class LoadsTruckRemoteModel(
    val id: Int?,
    @SerializedName("truck_type")
    val truckType: LoadsTruckTypeRemoteModel?
)
