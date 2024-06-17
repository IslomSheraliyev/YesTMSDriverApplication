package com.yestms.driver.android.domain.repository

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.loads.LoadModel
import kotlinx.coroutines.flow.Flow

interface LoadsRepository {
    fun getLoads(): Flow<PagingData<LoadModel>>
}
