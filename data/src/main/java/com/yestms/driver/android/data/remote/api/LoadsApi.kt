package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.LoadsUrl
import com.yestms.driver.android.data.remote.request.load.ReportProblemRequest
import com.yestms.driver.android.data.remote.request.load.UpdateLoadStatusRequest
import com.yestms.driver.android.data.remote.response.loads.AlertStatusesRemoteItemModel
import com.yestms.driver.android.data.remote.response.loads.LoadResponse
import com.yestms.driver.android.data.remote.response.loads.LoadsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface LoadsApi {

    @GET(LoadsUrl.LOADS)
    suspend fun getLoads(
        @Query("search") search: String? = null,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): LoadsResponse

    @GET(LoadsUrl.GET_LOAD)
    suspend fun getLoad(
        @Path("id") id: Int
    ): LoadResponse

    @PUT(LoadsUrl.LOADS)
    suspend fun updateLoadStatus(
        @Body body: UpdateLoadStatusRequest
    )

    @GET(LoadsUrl.ALERT_STATUSES)
    suspend fun alertStatuses(): List<AlertStatusesRemoteItemModel>

    @PUT(LoadsUrl.LOADS)
    suspend fun reportProblem(@Body body: ReportProblemRequest)

    @Multipart
    @PUT(LoadsUrl.LOADS)
    suspend fun uploadImages(
        @Part("id") id: RequestBody,
        @Part mediaBols: MultipartBody.Part,
        @Part lumpers: MultipartBody.Part?,
        @Part trailerPhotos: MultipartBody.Part?
    )
}