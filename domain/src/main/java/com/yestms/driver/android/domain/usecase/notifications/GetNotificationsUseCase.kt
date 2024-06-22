package com.yestms.driver.android.domain.usecase.notifications

import androidx.paging.PagingData
import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.model.notifications.NotificationModel
import com.yestms.driver.android.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) : UseCaseWithParams<GetNotificationsUseCase.Params, Flow<PagingData<NotificationModel>>>(Dispatchers.IO) {

    data class Params(
        val search: String?,
        val sort: String?,
        val dateTo: String?,
        val dateFrom: String?
    )

    override suspend fun execute(parameter: Params): Flow<PagingData<NotificationModel>> {
        return notificationsRepository.getNotices(
            search = parameter.search,
            sort = parameter.sort,
            dateTo = parameter.dateTo,
            dateFrom = parameter.dateFrom
        )
    }
}
