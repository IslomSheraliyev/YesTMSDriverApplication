package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverResponse
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverUserRemoteModel
import com.yestms.driver.android.domain.model.auth.login.AuthLoginDriverModel
import com.yestms.driver.android.domain.model.auth.login.AuthLoginDriverUserModel

object LoginDriverMapper {

    val loginDriverMapper:
            Mapper<AuthLoginDriverResponse?, AuthLoginDriverModel> =
        { remote ->
            AuthLoginDriverModel(
                token = remote?.token.orEmpty(),
                user = remote?.user.let(loginDriverUserMapper)
            )
        }

    private val loginDriverUserMapper:
            Mapper<AuthLoginDriverUserRemoteModel?, AuthLoginDriverUserModel> =
        { remote ->
            AuthLoginDriverUserModel(
                id = remote?.id.or0()
            )
        }
}