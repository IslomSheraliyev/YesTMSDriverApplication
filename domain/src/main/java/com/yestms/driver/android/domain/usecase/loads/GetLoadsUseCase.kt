package com.yestms.driver.android.domain.usecase.loads

import androidx.paging.PagingData
import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoadsUseCase @Inject constructor(
    private val loadsRepository: LoadsRepository
):UseCaseWithParams<GetLoadsUseCase.Params, Flow<PagingData<LoadModel>>>(Dispatchers.IO) {
    data class Params(
        val search: String?,
        val pickUpDateFrom: String?,
        val pickUpDateTo: String?,
        val deliveryDateFrom: String?,
        val deliveryDateTo: String?,
        val status: Float?,
        val brokers: Float?,
        val driver: Float?,
        val type: Float?
    )

    override suspend fun execute(parameter: Params): Flow<PagingData<LoadModel>> {
        return loadsRepository.getLoads(
            parameter.search,
            parameter.pickUpDateFrom,
            parameter.pickUpDateTo,
            parameter.deliveryDateFrom,
            parameter.deliveryDateTo,
            parameter.status,
            parameter.brokers,
            parameter.driver,
            parameter.type
        )
    }

}
