package com.yestms.driver.android.ui.screens.details

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.usecase.loads.GetAlertStatusesUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import com.yestms.driver.android.domain.usecase.loads.ReportProblemUseCase
import com.yestms.driver.android.domain.usecase.loads.UpdateLoadStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLoadUseCase: GetLoadUseCase,
    private val updateLoadStatusUseCase: UpdateLoadStatusUseCase,
    private val getAlertStatusesUseCase: GetAlertStatusesUseCase,
    private val reportProblemUseCase: ReportProblemUseCase
) : BaseViewModel() {

    private val _load = MutableStateFlow<LoadModel?>(null)
    val load = _load.asSharedFlow()

    private val _problems = MutableStateFlow<List<AlertStatusesItemModel>>(emptyList())
    val problems = _problems.asStateFlow()

    fun getDetails(id: Int) = vmScope.launch {
        delay(300L)
        getLoadUseCase(id).onSuccess { load ->
            _load.emit(load)
        }
    }

    fun updateLoadStatus(id: Int, loadStatusId: Int) = vmScope.launch {
        updateLoadStatusUseCase(id, loadStatusId)
            .onSuccess {
                getDetails(id)
            }.onFailure(::errorProcess)
    }

    fun getProblems() = vmScope.launch {
        getAlertStatusesUseCase()
            .onSuccess {
                _problems.emit(it)
            }.onFailure(::errorProcess)
    }

    fun reportProblem(loadId: Int, loadAlertStatusId: Int) = vmScope.launch {
        reportProblemUseCase(loadId, loadAlertStatusId)
            .onSuccess {
                getDetails(loadId)
            }
    }
}