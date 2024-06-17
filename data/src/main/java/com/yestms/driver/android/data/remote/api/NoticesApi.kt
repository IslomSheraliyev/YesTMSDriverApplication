package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.NoticesUrl
import com.yestms.driver.android.data.remote.response.notices.NoticesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticesApi {

    @GET(NoticesUrl.NOTICES)
    suspend fun getNotices(
        @Query("search") search: String? = null,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String? = null,
        @Query("dateTo") dateTo: String? = null,
        @Query("dateFrom") dateFrom: String? = null
    ): NoticesResponse

    @GET(NoticesUrl.UNREAD)
    suspend fun getUnreadCount(): Int
}