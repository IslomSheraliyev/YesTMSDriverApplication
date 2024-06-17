package com.yestms.driver.android.domain.model.loads

import androidx.compose.runtime.Immutable

@Immutable
data class LoadModel(
    val id: Int,
    val rate: Int,
    val mileage: Int,
    val pickUpLocation: String,
    val pickUpPolitical: String,
    val deliveryLocation: String,
    val deliveryPolitical: String,
    val loadStatus: LoadStatusModel,
)