package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.LoadsUrl
import com.yestms.driver.android.data.remote.response.loads.get.LoadsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoadsApi {

    @GET(LoadsUrl.LOADS)
    suspend fun getLoads(
        @Query("search") search: String? = null,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): LoadsResponse
}