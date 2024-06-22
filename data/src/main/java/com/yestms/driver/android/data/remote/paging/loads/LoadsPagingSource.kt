package com.yestms.driver.android.data.remote.paging.loads

import retrofit2.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yestms.driver.android.data.mapper.orFalse
import com.yestms.driver.android.data.mapper.LoadsMapper
import com.yestms.driver.android.data.remote.api.LoadsApi
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import java.io.IOException

class LoadsPagingSource(
    private val api: LoadsApi
) : PagingSource<Int, LoadsItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, LoadsItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LoadsItemModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getLoads(
                limit = 10,
                page = nextPageNumber
            )
            val nextNumber: Int? =
                if (response.rows?.isEmpty().orFalse() || response.rows?.size!! < 10) null
                else nextPageNumber + 1

            LoadResult.Page(
                data = response.rows?.map(LoadsMapper.loadItemMapper).orEmpty(),
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