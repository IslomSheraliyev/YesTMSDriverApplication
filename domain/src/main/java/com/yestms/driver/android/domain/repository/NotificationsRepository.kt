package com.yestms.driver.android.domain.repository

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.notifications.NotificationModel
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    suspend fun getNotices(
        search: String?,
        sort: String?,
        dateTo: String?,
        dateFrom: String?
    ): Flow<PagingData<NotificationModel>>

    suspend fun getUnreadCount(): Int

    suspend fun deleteAllNotifications(): Int
}