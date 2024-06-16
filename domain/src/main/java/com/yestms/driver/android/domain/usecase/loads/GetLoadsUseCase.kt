package com.yestms.driver.android.domain.usecase.loads

import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoadsUseCase @Inject constructor(
    private val loadsRepository: LoadsRepository
) {
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

    operator fun invoke(params: Params): Flow<PagingData<LoadModel>> {
        return loadsRepository.getLoads(
            params.search,
            params.pickUpDateFrom,
            params.pickUpDateTo,
            params.deliveryDateFrom,
            params.deliveryDateTo,
            params.status,
            params.brokers,
            params.driver,
            params.type
        )
    }
}
