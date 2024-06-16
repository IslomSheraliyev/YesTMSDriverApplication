package com.yestms.driver.android.data.remote.response.notices

data class NoticeRemoteModel(
    val id: Int?,
    val title: String?,
    val isActive: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val userId: Int?,
    val loadId: Int?
)