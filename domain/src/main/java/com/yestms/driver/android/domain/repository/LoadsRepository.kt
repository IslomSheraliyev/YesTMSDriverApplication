package com.yestms.driver.android.domain.repository

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import kotlinx.coroutines.flow.Flow

interface LoadsRepository {
    fun getLoads(
        search: String?,
        pickUpDateFrom: String?,
        pickUpDateTo: String?,
        deliveryDateFrom: String?,
        deliveryDateTo: String?,
        status: Float?,
        brokers: Float?,
        driver: Float?,
        type: Float?
    ): Flow<PagingData<LoadModel>>
}
