package com.yestms.driver.android.data.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yestms.driver.android.data.mapper.LoadsMapper
import com.yestms.driver.android.data.remote.api.LoadsApi
import com.yestms.driver.android.data.remote.paging.loads.LoadsPagingSource
import com.yestms.driver.android.data.remote.request.load.ReportProblemRequest
import com.yestms.driver.android.data.remote.request.load.UpdateLoadStatusRequest
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class LoadsRepositoryImpl @Inject constructor(
    private val api: LoadsApi
) : LoadsRepository {
    override suspend fun getLoads(): Flow<PagingData<LoadsItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                LoadsPagingSource(api)
            }
        ).flow
    }

    override suspend fun getLoad(id: Int): LoadModel {
        return api.getLoad(id).let(LoadsMapper.loadMapper)
    }

    override suspend fun updateLoadStatus(id: Int, loadStatusId: Int) {
        api.updateLoadStatus(UpdateLoadStatusRequest(id, loadStatusId))
    }

    override suspend fun getAlertStatuses(): List<AlertStatusesItemModel> {
        return api.alertStatuses().map(LoadsMapper.loadAlertStatusesMapper)
    }

    override suspend fun reportProblem(id: Int, loadAlertStatusId: Int) {
        api.reportProblem(ReportProblemRequest(id, loadAlertStatusId))
    }

    override suspend fun uploadImages(
        id: Int,
        mediaBolsUri: Uri,
        lumpersUri: Uri?,
        trailerPhotosUri: Uri?,
        contentResolver: ContentResolver
    ) {
        val idRequestBody = id.toString().toRequestBody(MultipartBody.FORM)

        val mediaBolsPart = createPartFromUri("media_bols", mediaBolsUri, contentResolver)
        val lumpersPart = lumpersUri?.let { createPartFromUri("lumpers", it, contentResolver) }
        val trailerPhotosPart =
            trailerPhotosUri?.let { createPartFromUri("trailer_photos", it, contentResolver) }

        api.uploadImages(idRequestBody, mediaBolsPart, lumpersPart, trailerPhotosPart)
    }


    private fun createPartFromUri(
        name: String,
        uri: Uri,
        contentResolver: ContentResolver
    ): MultipartBody.Part {
        val inputStream = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes()
        inputStream?.close()

        val requestBody = fileBytes?.toRequestBody("application/octet-stream".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, name, requestBody!!)
    }

}
