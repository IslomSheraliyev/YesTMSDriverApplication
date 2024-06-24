package com.yestms.driver.android.domain.model.loads

import androidx.compose.runtime.Immutable

@Immutable
data class LoadsItemModel(
    val id: Int,
    val loadId: String,
    val rate: Long,
    val mileage: Int,
    val pickUpLocation: String,
    val pickUpPolitical: String,
    val deliveryLocation: String,
    val deliveryPolitical: String,
    val loadStatus: LoadStatusModel,
)