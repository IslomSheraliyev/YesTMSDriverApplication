package com.yestms.driver.android.data.remote.response.loads.get

import com.google.gson.annotations.SerializedName

data class LoadsDriverRemoteModel(
    val fullName: String?,
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val truckId: Int?,
    val driverRate: Int?,
    @SerializedName("driver_type")
    val driverType: LoadsDriverTypeRemoteModel?,
    val truck: LoadsTruckRemoteModel?,
    @SerializedName("driver_loads")
    val driverLoads: LoadsDriverLoadsRemoteModel?
)
