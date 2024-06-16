package com.yestms.driver.android.data.remote.paging.notices

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yestms.driver.android.data.mapper.common.orFalse
import com.yestms.driver.android.data.mapper.notices.NoticesMapper
import com.yestms.driver.android.data.remote.api.NoticesApi
import com.yestms.driver.android.domain.model.notices.NoticeModel
import retrofit2.HttpException
import java.io.IOException

class NoticesPagingSource(
    private val api: NoticesApi,
    private val sort: String?,
    private val dateTo: String?,
    private val dateFrom: String?,
    private val search: String?
) : PagingSource<Int, NoticeModel>() {
    override fun getRefreshKey(state: PagingState<Int, NoticeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoticeModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getNotices(
                limit = 10,
                page = nextPageNumber,
                sort = sort,
                dateTo = dateTo,
                dateFrom = dateFrom,
                search = search
            )
            val nextNumber: Int? =
                if (response.rows?.isEmpty().orFalse() || response.rows?.size!! < 10) null
                else nextPageNumber + 1

            LoadResult.Page(
                data = response.rows?.map(NoticesMapper.noticeMapper).orEmpty(),
                prevKey = null,
                nextKey = nextNumber
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}