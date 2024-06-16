package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.LoadsUrl
import com.yestms.driver.android.data.remote.response.loads.get.GetLoadsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LoadsApi {

    @GET(LoadsUrl.LOADS)
    suspend fun getLoads(
        @Query("search") search: String? = null,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("pickUpDateFrom") pickUpDateFrom: String? = null,
        @Query("pickUpDateTo") pickUpDateTo: String? = null,
        @Query("deliveryDateFrom") deliveryDateFrom: String? = null,
        @Query("deliveryDateTo") deliveryDateTo: String? = null,
        @Query("status") status: Float? = null,
        @Query("brokers") brokers: Float? = null,
        @Query("driver") driver: Float? = null,
        @Query("type") type: Float? = null
    ): GetLoadsResponse
}