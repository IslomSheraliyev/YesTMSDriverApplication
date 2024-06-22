package com.yestms.driver.android.ui.screens.details

import android.util.Log
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLoadUseCase: GetLoadUseCase
) : BaseViewModel() {

    private val _load = MutableStateFlow<LoadModel?>(null)
    val load = _load.asSharedFlow()

    fun getDetails(id: Int) = vmScope.launch {
        getLoadUseCase(id).onSuccess { load ->
            _load.emit(load)
        }.onFailure { exception ->
            Log.d("nalmi", "getDetails: ${exception.message}")
        }
    }
}