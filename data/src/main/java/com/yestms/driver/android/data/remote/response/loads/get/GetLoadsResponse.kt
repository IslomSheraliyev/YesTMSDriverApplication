package com.yestms.driver.android.data.remote.response.loads.get

data class GetLoadsResponse(
    val rows: List<LoadRemoteModel>?,
    val count: Int?
)