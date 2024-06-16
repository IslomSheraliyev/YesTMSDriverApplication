package com.yestms.driver.android.data.mapper.notices

import com.yestms.driver.android.data.mapper.common.Mapper
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.data.mapper.common.orFalse
import com.yestms.driver.android.data.remote.response.notices.NoticeRemoteModel
import com.yestms.driver.android.data.remote.response.notices.NoticesResponse
import com.yestms.driver.android.domain.model.notices.NoticeModel
import com.yestms.driver.android.domain.model.notices.NoticesModel

object NoticesMapper {

    val noticesMapper:
            Mapper<NoticesResponse?, NoticesModel> =
        { remote ->
            NoticesModel(
                count = remote?.count.or0(),
                rows = remote?.rows?.map(noticeMapper).orEmpty()
            )
        }

    val noticeMapper:
            Mapper<NoticeRemoteModel?, NoticeModel> =
        { remote ->
            NoticeModel(
                id = remote?.id.or0(),
                title = remote?.title.orEmpty(),
                isActive = remote?.isActive.orFalse(),
                createdAt = remote?.createdAt.orEmpty(),
                updatedAt = remote?.updatedAt.orEmpty(),
                userId = remote?.userId.or0(),
                loadId = remote?.loadId.or0()
            )
        }
}