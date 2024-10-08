package com.yestms.driver.android.domain.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.paging.PagingData
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import kotlinx.coroutines.flow.Flow

interface LoadsRepository {
    suspend fun getLoads(): Flow<PagingData<LoadsItemModel>>

    suspend fun getLoad(id: Int): LoadModel

    suspend fun updateLoadStatus(id: Int, loadStatusId: Int)

    suspend fun getAlertStatuses(): List<AlertStatusesItemModel>

    suspend fun reportProblem(id: Int, loadAlertStatusId: Int)

    suspend fun uploadImages(
        id: Int,
        mediaBolsUri: Uri,
        lumpersUri: Uri?,
        trailerPhotosUri: Uri?,
        contentResolver: ContentResolver
    )
}
