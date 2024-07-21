package com.yestms.driver.android.ui.screens.loads

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import com.yestms.driver.android.domain.usecase.socket.AddUserUseCase
import com.yestms.driver.android.domain.usecase.socket.ConnectSocketUseCase
import com.yestms.driver.android.domain.usecase.socket.DisconnectSocketUseCase
import com.yestms.driver.android.domain.usecase.socket.KickUserUseCase
import com.yestms.driver.android.domain.usecase.socket.RenderDispatcherDashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadsViewModel @Inject constructor(
    private val getLoadsUseCase: GetLoadsUseCase,
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val kickUserUseCase: KickUserUseCase,
    private val renderDispatcherDashboardUseCase: RenderDispatcherDashboardUseCase
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
        kickUserUseCase(
            parameter = AppPreferences.currentUserId
        ).onSuccess {
            disconnectSocketUseCase()
        }
    }

    fun connect() = vmScope.launch {
        renderDispatcherDashboardUseCase {
            getLoads()
        }.onSuccess {
            connectSocketUseCase().onSuccess {
                addUserUseCase(
                    parameter1 = AppPreferences.currentUserId,
                    parameter2 = AppPreferences.currentRoleId
                )
            }
        }
    }
}
