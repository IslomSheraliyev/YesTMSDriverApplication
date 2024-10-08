package com.yestms.driver.android.data.remote.api

import com.yestms.driver.android.data.constants.NotificationsUrl
import com.yestms.driver.android.data.remote.request.notifications.ViewNotificationRequest
import com.yestms.driver.android.data.remote.response.notifications.NotificationsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationsApi {

    @GET(NotificationsUrl.NOTIFICATIONS)
    suspend fun getNotifications(
        @Query("search") search: String? = null,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String? = null,
        @Query("dateTo") dateTo: String? = null,
        @Query("dateFrom") dateFrom: String? = null
    ): NotificationsResponse

    @GET(NotificationsUrl.UNREAD)
    suspend fun getUnreadCount(): Int

    @DELETE(NotificationsUrl.NOTIFICATIONS)
    suspend fun deleteNotifications(): Int

    @DELETE(NotificationsUrl.NOTIFICATIONS_DELETE_ONE)
    suspend fun deleteNotification(
        @Path("id") id: Int
    ): Int

    @PUT(NotificationsUrl.NOTIFICATIONS)
    suspend fun viewNotification(
        @Body body: ViewNotificationRequest
    ): Int
}