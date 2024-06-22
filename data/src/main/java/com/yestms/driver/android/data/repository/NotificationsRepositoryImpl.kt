package com.yestms.driver.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yestms.driver.android.data.remote.api.NotificationsApi
import com.yestms.driver.android.data.remote.paging.notices.NotificationsPagingSource
import com.yestms.driver.android.domain.model.notifications.NotificationModel
import com.yestms.driver.android.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val api: NotificationsApi
) : NotificationsRepository {

    override suspend fun getNotices(
        search: String?,
        sort: String?,
        dateTo: String?,
        dateFrom: String?
    ): Flow<PagingData<NotificationModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NotificationsPagingSource(
                    api = api,
                    sort = sort,
                    dateTo = dateTo,
                    dateFrom = dateFrom,
                    search = search
                )
            }
        ).flow
    }

    override suspend fun getUnreadCount(): Int {
        return api.getUnreadCount()
    }

    override suspend fun deleteAllNotifications(): Int {
        return api.deleteAllNotifications()
    }

}