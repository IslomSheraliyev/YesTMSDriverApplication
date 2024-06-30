package com.yestms.driver.android.domain.model.loads

data class LoadModel(
    val id: Int,
    val loadId: String,
    val activationLink: String,
    val rate: Long,
    val mileage: Int,
    val pickUpNote: String,
    val pickUpLocation: String,
    val pickUpPolitical: String,
    val deliveryLocation: String,
    val deliveryPolitical: String,
    val loadStatus: LoadStatusModel,
    val loadAlertLogs: List<LoadAlertsLogsItemModel>
)