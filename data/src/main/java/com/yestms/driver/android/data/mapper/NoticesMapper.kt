package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.notices.NoticeRemoteModel
import com.yestms.driver.android.domain.model.notices.NoticeModel

object NoticesMapper {

    val noticeMapper:
            Mapper<NoticeRemoteModel?, NoticeModel> =
        { remote ->
            NoticeModel(
                id = remote?.id.or0(),
                title = remote?.title.orEmpty(),
                isActive = remote?.isActive.orFalse(),
                createdAt = remote?.createdAt.orEmpty(),
                userId = remote?.userId.or0(),
                loadId = remote?.loadId.or0()
            )
        }
}