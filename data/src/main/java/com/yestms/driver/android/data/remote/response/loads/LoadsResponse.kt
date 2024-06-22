package com.yestms.driver.android.data.remote.response.loads

data class LoadsResponse(
    val rows: List<LoadsItemRemoteModel>?,
    val count: Int?
)