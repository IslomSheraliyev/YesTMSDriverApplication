package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.notifications.NotificationsRemoteModel
import com.yestms.driver.android.domain.model.notifications.NotificationModel

object NotificationsMapper {

    val noticeMapper:
            Mapper<NotificationsRemoteModel?, NotificationModel> =
        { remote ->
            NotificationModel(
                id = remote?.id.or0(),
                title = remote?.title.orEmpty(),
                isActive = remote?.isActive.orFalse(),
                createdAt = remote?.createdAt.orEmpty(),
                userId = remote?.userId.or0(),
                loadId = remote?.loadId.or0()
            )
        }
}