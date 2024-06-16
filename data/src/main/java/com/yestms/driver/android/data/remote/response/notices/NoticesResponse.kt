package com.yestms.driver.android.data.remote.response.notices

data class NoticesResponse(
    val count: Int?,
    val rows: List<NoticeRemoteModel>?
)