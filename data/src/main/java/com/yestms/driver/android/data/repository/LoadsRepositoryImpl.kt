package com.yestms.driver.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yestms.driver.android.data.remote.api.LoadsApi
import com.yestms.driver.android.data.remote.paging.loads.LoadsPagingSource
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadsRepositoryImpl @Inject constructor(
    private val api: LoadsApi
) : LoadsRepository {
    override fun getLoads(
        search: String?,
        pickUpDateFrom: String?,
        pickUpDateTo: String?,
        deliveryDateFrom: String?,
        deliveryDateTo: String?,
        status: Float?,
        brokers: Float?,
        driver: Float?,
        type: Float?
    ): Flow<PagingData<LoadModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                LoadsPagingSource(api)
            }
        ).flow
    }
}
