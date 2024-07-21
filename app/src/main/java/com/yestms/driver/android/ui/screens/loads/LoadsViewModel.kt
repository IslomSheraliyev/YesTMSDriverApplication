package com.yestms.driver.android.ui.screens.loads

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketAddUserUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketConnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketDisconnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketKickUserUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketRenderDispatcherDashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadsViewModel @Inject constructor(
    private val getLoadsUseCase: GetLoadsUseCase,
    private val socketConnectUseCase: SocketConnectUseCase,
    private val socketDisconnectUseCase: SocketDisconnectUseCase,
    private val socketAddUserUseCase: SocketAddUserUseCase,
    private val socketKickUserUseCase: SocketKickUserUseCase,
    private val socketRenderDispatcherDashboardUseCase: SocketRenderDispatcherDashboardUseCase
) : BaseViewModel() {

    private val _loads = MutableStateFlow<PagingData<LoadsItemModel>>(PagingData.empty())
    val loads: StateFlow<PagingData<LoadsItemModel>> get() = _loads

    fun getLoads() = vmScope.launch {
        _isRefreshing.emit(true)
        getLoadsUseCase().onSuccess { result ->
            _isRefreshing.emit(false)
            result.cachedIn(vmScope)
                .collectLatest { data ->
                    _loads.emit(data)
                }
        }.onFailure {
            _isRefreshing.emit(false)
        }
    }

    fun disconnect() = vmScope.launch {
        socketKickUserUseCase(
            parameter = AppPreferences.currentUserId
        ).onSuccess {
            socketDisconnectUseCase()
        }
    }

    fun connect() = vmScope.launch {
        socketRenderDispatcherDashboardUseCase {
            getLoads()
        }.onSuccess {
            socketConnectUseCase().onSuccess {
                socketAddUserUseCase(
                    parameter1 = AppPreferences.currentUserId,
                    parameter2 = AppPreferences.currentRoleId
                )
            }
        }
    }
}
