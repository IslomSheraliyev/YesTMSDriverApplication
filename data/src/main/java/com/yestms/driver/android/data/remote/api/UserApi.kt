package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.UserUrl
import com.yestms.driver.android.data.remote.response.stats.DriverStatsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET(UserUrl.GET_DRIVER_STATS)
    suspend fun getDriverStats(
        @Path("id") id: Int,
        @Query("period") period: String
    ): DriverStatsResponse
}