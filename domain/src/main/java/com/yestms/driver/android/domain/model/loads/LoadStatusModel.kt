package com.yestms.driver.android.domain.model.loads

import com.yestms.driver.android.domain.enums.DriverDetailsLoadStatus

data class LoadStatusModel(
    val id: Int,
    val name: DriverDetailsLoadStatus,
    val color: String
)
