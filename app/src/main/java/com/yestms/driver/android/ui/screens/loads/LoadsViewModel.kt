package com.yestms.driver.android.ui.screens.loads

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadsViewModel @Inject constructor(
    private val getLoadsUseCase: GetLoadsUseCase
) : BaseViewModel() {

    private val _loads = MutableStateFlow<PagingData<LoadModel>>(PagingData.empty())
    val loads: StateFlow<PagingData<LoadModel>> get() = _loads

    fun getLoads(
        search: String? = null,
        pickUpDateFrom: String? = null,
        pickUpDateTo: String? = null,
        deliveryDateFrom: String? = null,
        deliveryDateTo: String? = null,
        status: Float? = null,
        brokers: Float? = null,
        driver: Float? = null,
        type: Float? = null
    ) = vmScope.launch {

        _isRefreshing.emit(true)

        getLoadsUseCase(
            GetLoadsUseCase.Params(
                search,
                pickUpDateFrom,
                pickUpDateTo,
                deliveryDateFrom,
                deliveryDateTo,
                status,
                brokers,
                driver,
                type
            )
        ).onSuccess { result ->
            _isRefreshing.emit(false)
            result.cachedIn(vmScope)
                .collectLatest { data ->
                    _loads.emit(data)
                }
        }.onFailure(::errorProcess)
    }
}
