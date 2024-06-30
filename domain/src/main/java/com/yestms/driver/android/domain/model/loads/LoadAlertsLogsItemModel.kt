package com.yestms.driver.android.domain.model.loads

data class LoadAlertsLogsItemModel(
    val id: Int,
    val createdAt: String,
    val loadAlertStatus: LoadAlertStatusModel
)