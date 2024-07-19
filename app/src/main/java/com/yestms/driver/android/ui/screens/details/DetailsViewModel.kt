package com.yestms.driver.android.ui.screens.details

import android.content.ContentResolver
import android.net.Uri
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.data.local.AppPreferences
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.model.loads.DispatcherModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.usecase.loads.GetAlertStatusesUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import com.yestms.driver.android.domain.usecase.loads.ReportProblemUseCase
import com.yestms.driver.android.domain.usecase.loads.UpdateLoadStatusUseCase
import com.yestms.driver.android.domain.usecase.loads.UploadImagesUseCase
import com.yestms.driver.android.domain.usecase.socket.ChangeForDashboardUseCase
import com.yestms.driver.android.domain.usecase.socket.SendNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLoadUseCase: GetLoadUseCase,
    private val updateLoadStatusUseCase: UpdateLoadStatusUseCase,
    private val getAlertStatusesUseCase: GetAlertStatusesUseCase,
    private val reportProblemUseCase: ReportProblemUseCase,
    private val uploadImagesUseCase: UploadImagesUseCase,
    private val changeForDashboardUseCase: ChangeForDashboardUseCase,
    private val sendNoticeUseCase: SendNoticeUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(DetailsUIState())
    val uiState = _uiState.asStateFlow()

    private val _load = MutableStateFlow<LoadModel?>(null)
    val load = _load.asStateFlow()


    private val _problems = MutableStateFlow<List<AlertStatusesItemModel>>(emptyList())
    val problems = _problems.asStateFlow()

    fun getDetails(id: Int) = vmScope.launch {
        getLoadUseCase(id).onSuccess { load ->
            _load.emit(load)
        }
    }

    fun updateLoadStatus(id: Int, loadStatusId: Int, dispatchers: List<DispatcherModel>) =
        vmScope.launch {
            updateLoadStatusUseCase(id, loadStatusId)
                .onSuccess {
                    getDetails(id)
                    changeForDashboardUseCase(dispatchers.map { it.id })
                    sendNoticeUseCase(
                        parameter1 = dispatchers.map { it.id },
                        parameter2 = "Load ${load.value?.loadId} changed status on ${load.value?.loadStatus?.name} by driver ${AppPreferences.fullName}"
                    )
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

    fun uploadImages(
        id: Int,
        pdf: Uri,
        lumper: Uri?,
        trailerPhoto: Uri?,
        contentResolver: ContentResolver
    ) = vmScope.launch {
        uploadImagesUseCase(id, pdf, lumper, trailerPhoto, contentResolver)
            .onSuccess {

            }
    }

    fun changeMediaBolUploadedState(isUploaded: Boolean) =
        _uiState.update { it.copy(isMediaBolUploaded = isUploaded) }

    fun changeLumperUploadedState(isUploaded: Boolean) =
        _uiState.update { it.copy(isLumperUploaded = isUploaded) }

    fun changeTrailerPhotoUploadedState(isUploaded: Boolean) =
        _uiState.update { it.copy(isTrailerPhotoUploaded = isUploaded) }

}

data class DetailsUIState(
    val isMediaBolUploaded: Boolean = false,
    val isLumperUploaded: Boolean = false,
    val isTrailerPhotoUploaded: Boolean = false,
)