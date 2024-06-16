package com.yestms.driver.android.domain.model.loads.get



data class LoadsDriverModel(
    val fullName: String,
    val id: Int,
    val firstName: String,
    val lastName: String,
    val truckId: Int,
    val driverRate: Int,
    val driverType: LoadsDriverTypeModel,
    val truck: LoadsTruckModel,
    val driverLoads: LoadsDriverLoadsModel
)
