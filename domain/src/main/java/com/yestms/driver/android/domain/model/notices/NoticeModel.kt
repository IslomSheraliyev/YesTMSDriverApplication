package com.yestms.driver.android.domain.model.notices

import androidx.compose.runtime.Immutable

@Immutable
data class NoticeModel(
    val id: Int,
    val title: String,
    val isActive: Boolean,
    val createdAt: String,
    val userId: Int,
    val loadId: Int
)