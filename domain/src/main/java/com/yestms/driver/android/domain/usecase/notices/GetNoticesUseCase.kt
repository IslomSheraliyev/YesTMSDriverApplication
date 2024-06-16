package com.yestms.driver.android.domain.usecase.notices

import androidx.paging.PagingData
import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.model.notices.NoticeModel
import com.yestms.driver.android.domain.repository.NoticesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticesUseCase @Inject constructor(
    private val noticesRepository: NoticesRepository
) : UseCaseWithParams<GetNoticesUseCase.Params, Flow<PagingData<NoticeModel>>>(Dispatchers.IO) {

    data class Params(
        val search: String?,
        val sort: String?,
        val dateTo: String?,
        val dateFrom: String?
    )

    override suspend fun execute(parameter: Params): Flow<PagingData<NoticeModel>> {
        return noticesRepository.getNotices(
            search = parameter.search,
            sort = parameter.sort,
            dateTo = parameter.dateTo,
            dateFrom = parameter.dateFrom
        )
    }
}
