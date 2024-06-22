package com.yestms.driver.android.domain.model.notifications

import androidx.compose.runtime.Immutable

@Immutable
data class NotificationModel(
    val id: Int,
    val title: String,
    val isActive: Boolean,
    val createdAt: String,
    val userId: Int,
    val loadId: Int
)