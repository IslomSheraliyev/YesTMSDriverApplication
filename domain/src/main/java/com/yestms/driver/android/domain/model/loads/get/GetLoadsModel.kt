package com.yestms.driver.android.domain.model.loads.get

data class GetLoadsModel(
    val rows: List<LoadModel>,
    val count: Int
)