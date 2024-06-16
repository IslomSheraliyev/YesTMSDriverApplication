package com.yestms.driver.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yestms.driver.android.data.remote.api.NoticesApi
import com.yestms.driver.android.data.remote.paging.notices.NoticesPagingSource
import com.yestms.driver.android.domain.model.notices.NoticeModel
import com.yestms.driver.android.domain.repository.NoticesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoticesRepositoryImpl @Inject constructor(
    private val api: NoticesApi
) : NoticesRepository {

    override suspend fun getNotices(
        search: String?,
        sort: String?,
        dateTo: String?,
        dateFrom: String?
    ): Flow<PagingData<NoticeModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NoticesPagingSource(
                    api = api,
                    sort = sort,
                    dateTo = dateTo,
                    dateFrom = dateFrom,
                    search = search
                )
            }
        ).flow
    }

}