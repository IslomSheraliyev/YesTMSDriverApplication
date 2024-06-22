package com.yestms.driver.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yestms.driver.android.data.mapper.LoadsMapper
import com.yestms.driver.android.data.remote.api.LoadsApi
import com.yestms.driver.android.data.remote.paging.loads.LoadsPagingSource
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadsRepositoryImpl @Inject constructor(
    private val api: LoadsApi
) : LoadsRepository {
    override suspend fun getLoads(): Flow<PagingData<LoadsItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                LoadsPagingSource(api)
            }
        ).flow
    }

    override suspend fun getLoad(id: Int): LoadModel {
        return api.getLoad(id).let(LoadsMapper.loadMapper)
    }
}
