package com.yestms.driver.android.ui.screens.notifications

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.model.notifications.NotificationModel
import com.yestms.driver.android.domain.usecase.notifications.DeleteAllNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetNotificationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val deleteAllNotificationsUseCase: DeleteAllNotificationsUseCase
) : BaseViewModel() {

    private val _notifications = MutableStateFlow<PagingData<NotificationModel>>(PagingData.empty())
    val notifications: StateFlow<PagingData<NotificationModel>> get() = _notifications

    private val _notificationsCount = MutableStateFlow(0)
    val notificationsCount = _notificationsCount.asStateFlow()

    fun getNotices(
        search: String? = null,
        sort: String? = null,
        dateTo: String? = null,
        dateFrom: String? = null
    ) = vmScope.launch {
        _notifications.emit(PagingData.empty())
        getNotificationsUseCase(
            GetNotificationsUseCase.Params(
                search = search,
                sort = sort,
                dateTo = dateTo,
                dateFrom = dateFrom
            )
        ).onSuccess { result ->
            result.cachedIn(vmScope)
                .collectLatest { data ->
                    _notifications.emit(data)
                }
        }.onFailure(::errorProcess)
    }

    fun deleteAllNotifications() = vmScope.launch {
        deleteAllNotificationsUseCase()
            .onSuccess { count ->
                _notificationsCount.emit(count)
            }
            .onFailure(::errorProcess)
    }
}
