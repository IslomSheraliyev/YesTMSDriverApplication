package com.yestms.driver.android.ui.screens.notifications

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.model.notifications.NotificationModel
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCase
import com.yestms.driver.android.domain.usecase.notifications.ViewNotificationUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketConnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketReceiveNoticeUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketSendNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val deleteNotificationsUseCase: DeleteNotificationsUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val viewNotificationUseCase: ViewNotificationUseCase,
    private val getUnreadCountUseCase: GetUnreadCountUseCase,
    private val socketConnectUseCase: SocketConnectUseCase,
    private val socketSendNoticeUseCase: SocketSendNoticeUseCase,
    private val receiveNoticeUseCase: SocketReceiveNoticeUseCase
) : BaseViewModel() {

    private val _notifications = MutableStateFlow<PagingData<NotificationModel>>(PagingData.empty())
    val notifications: StateFlow<PagingData<NotificationModel>> get() = _notifications
    private val _shouldUpdate = Channel<Unit>(Channel.BUFFERED)
    val shouldUpdate = _shouldUpdate.receiveAsFlow()

    fun getNotifications(
        search: String? = null,
        sort: String? = null,
        dateTo: String? = null,
        dateFrom: String? = null
    ) = vmScope.launch {
        _notifications.emit(PagingData.empty())
        _isRefreshing.emit(true)
        getNotificationsUseCase(
            GetNotificationsUseCase.Params(
                search = search,
                sort = sort,
                dateTo = dateTo,
                dateFrom = dateFrom
            )
        ).onSuccess { result ->
            _isRefreshing.emit(false)
            result.cachedIn(vmScope)
                .collectLatest { data ->
                    _notifications.emit(data)
                }
        }.onFailure {
            _isRefreshing.emit(false)
        }
    }

    fun deleteNotifications() = vmScope.launch {
        deleteNotificationsUseCase()
            .onSuccess {
                getNotificationsCount()
                socketSendNoticeUseCase(
                    parameter1 = listOf(AppPreferences.currentUserId),
                    parameter2 = "",
                    parameter3 = "info"
                )
            }
            .onFailure(::errorProcess)
    }

    fun connect() = vmScope.launch {
        receiveNoticeUseCase {
            vmScope.launch { _shouldUpdate.send(Unit) }
        }.onSuccess {
            socketConnectUseCase()
        }
    }

    fun deleteNotification(id: Int) = vmScope.launch {
        deleteNotificationUseCase(id)
        socketSendNoticeUseCase(
            parameter1 = listOf(AppPreferences.currentUserId),
            parameter2 = "",
            parameter3 = "info"
        )
    }

    fun viewNotification(id: Int) = vmScope.launch {
        viewNotificationUseCase(id, false)
        socketSendNoticeUseCase(
            parameter1 = listOf(AppPreferences.currentUserId),
            parameter2 = "",
            parameter3 = "info"
        )
    }

    private fun getNotificationsCount() = vmScope.launch {
        getUnreadCountUseCase()
    }
}
