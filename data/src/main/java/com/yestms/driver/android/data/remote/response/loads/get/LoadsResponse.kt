package com.yestms.driver.android.data.remote.response.loads.get

data class LoadsResponse(
    val rows: List<LoadRemoteModel>?,
    val count: Int?
)