package com.yestms.driver.android.domain.repository

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.notices.NoticeModel
import kotlinx.coroutines.flow.Flow

interface NoticesRepository {
    suspend fun getNotices(
        search: String?,
        sort: String?,
        dateTo: String?,
        dateFrom: String?
    ): Flow<PagingData<NoticeModel>>
}