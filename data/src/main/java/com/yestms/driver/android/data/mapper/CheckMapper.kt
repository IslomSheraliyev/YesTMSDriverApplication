package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckResponse
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckUserRemoteModel
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckUserRoleRemoteModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckUserModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckUserRoleModel

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
                fullName = remote?.fullName.orEmpty(),
                isOnDuty = remote?.isOnDuty.orFalse(),
                userRole = remote?.user_role.let(userRoleMapper)
            )
        }

    private val userRoleMapper: Mapper<AuthCheckUserRoleRemoteModel?, AuthCheckUserRoleModel> =
        { remote ->
            AuthCheckUserRoleModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                description = remote?.description.orEmpty()
            )
        }
}