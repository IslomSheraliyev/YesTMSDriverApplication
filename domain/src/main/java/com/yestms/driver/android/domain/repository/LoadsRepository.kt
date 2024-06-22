package com.yestms.driver.android.domain.repository

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import kotlinx.coroutines.flow.Flow

interface LoadsRepository {
    suspend fun getLoads(): Flow<PagingData<LoadsItemModel>>

    suspend fun getLoad(id: Int): LoadModel
}
