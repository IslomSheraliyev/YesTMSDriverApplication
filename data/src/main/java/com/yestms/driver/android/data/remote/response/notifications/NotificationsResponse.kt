package com.yestms.driver.android.data.remote.response.notifications

data class NotificationsResponse(
    val count: Int?,
    val rows: List<NotificationsRemoteModel>?
)