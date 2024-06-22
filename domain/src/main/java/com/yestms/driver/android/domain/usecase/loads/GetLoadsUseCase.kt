package com.yestms.driver.android.domain.usecase.loads

import androidx.paging.PagingData
import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoadsUseCase @Inject constructor(
    private val loadsRepository: LoadsRepository
) : UseCase<Flow<PagingData<LoadsItemModel>>>(Dispatchers.IO) {

    override suspend fun execute(): Flow<PagingData<LoadsItemModel>> {
        return loadsRepository.getLoads()
    }

}
