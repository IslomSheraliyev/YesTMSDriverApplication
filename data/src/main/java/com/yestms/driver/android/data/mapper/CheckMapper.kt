package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckResponse
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckUserRemoteModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckUserModel

object CheckMapper {

    val checkMapper: Mapper<AuthCheckResponse?, AuthCheckModel> =
        { remote ->
            AuthCheckModel(
                token = remote?.token.orEmpty(),
                user = remote?.user.let(checkUserMapper)
            )
        }

    private val checkUserMapper: Mapper<AuthCheckUserRemoteModel?, AuthCheckUserModel> =
        { remote ->
            AuthCheckUserModel(
                isOnDuty = remote?.isOnDuty.orFalse()
            )
        }
}