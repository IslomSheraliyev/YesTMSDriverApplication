package com.yestms.driver.android.data.mapper.auth.common

import com.yestms.driver.android.data.mapper.common.Mapper
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.data.remote.response.auth.common.AuthDispatcherRemoteModel
import com.yestms.driver.android.data.remote.response.auth.common.AuthDispatchersRemoteModel
import com.yestms.driver.android.domain.model.auth.common.AuthDispatcherModel
import com.yestms.driver.android.domain.model.auth.common.AuthDispatchersModel

object AuthCommonMapper {

    val dispatchersMapper:
            Mapper<AuthDispatchersRemoteModel?, AuthDispatchersModel> =
        { remote ->
            AuthDispatchersModel(
                id = remote?.id.or0(),
                dispatcherAssigned = remote?.dispatcherAssigned.let(dispatcherMapper)
            )
        }

    private val dispatcherMapper:
            Mapper<AuthDispatcherRemoteModel?, AuthDispatcherModel> =
        { remote ->
            AuthDispatcherModel(
                id = remote?.id.or0(),
                userId = remote?.userId.or0(),
                dispatcherId = remote?.dispatcherId.or0()
            )
        }
}