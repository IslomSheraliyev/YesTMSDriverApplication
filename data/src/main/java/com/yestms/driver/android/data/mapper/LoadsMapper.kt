package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.loads.get.LoadRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadStatusRemoteModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadStatusModel

object LoadsMapper {

     val loadMapper:
             Mapper<LoadRemoteModel?, LoadModel> =
        { remote ->
            LoadModel(
                id = remote?.id.or0(),
                rate = remote?.rate.or0(),
                mileage = remote?.mileage.or0(),
                pickUpLocation = remote?.pickUpLocation.orEmpty(),
                pickUpPolitical = remote?.pickUpPolitical.orEmpty(),
                deliveryLocation = remote?.deliveryLocation.orEmpty(),
                deliveryPolitical = remote?.deliveryPolitical.orEmpty(),
                loadStatus = remote?.loadStatus.let(loadStatusMapper),
            )
        }

    private val loadStatusMapper:
            Mapper<LoadStatusRemoteModel?, LoadStatusModel> =
        { remote ->
            LoadStatusModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                color = remote?.color.orEmpty()
            )
        }
}