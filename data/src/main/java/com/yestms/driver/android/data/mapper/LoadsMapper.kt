package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.loads.LoadResponse
import com.yestms.driver.android.data.remote.response.loads.LoadsItemRemoteModel
import com.yestms.driver.android.data.remote.response.loads.LoadStatusRemoteModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.model.loads.LoadStatusModel

object LoadsMapper {

    val loadItemMapper:
            Mapper<LoadsItemRemoteModel?, LoadsItemModel> =
        { remote ->
            LoadsItemModel(
                id = remote?.id.or0(),
                loadId = remote?.loadId.orEmpty(),
                rate = remote?.rate.or0(),
                mileage = remote?.mileage.or0(),
                pickUpLocation = remote?.pickUpLocation.orEmpty(),
                pickUpPolitical = remote?.pickUpPolitical.orEmpty(),
                deliveryLocation = remote?.deliveryLocation.orEmpty(),
                deliveryPolitical = remote?.deliveryPolitical.orEmpty(),
                loadStatus = remote?.loadStatus.let(loadStatusMapper),
            )
        }

    val loadMapper:
            Mapper<LoadResponse?, LoadModel> =
        { remote ->
            LoadModel(
                id = remote?.id.or0(),
                loadId = remote?.loadId.orEmpty(),
                activationLink = remote?.activationLink.orEmpty(),
                rate = remote?.rate.or0(),
                mileage = remote?.mileage.or0(),
                pickUpNote = remote?.pickUpNote.orEmpty(),
                pickUpLocation = remote?.pickUpLocation.orEmpty(),
                pickUpPolitical = remote?.pickUpPolitical.orEmpty(),
                deliveryLocation = remote?.deliveryLocation.orEmpty(),
                deliveryPolitical = remote?.deliveryPolitical.orEmpty(),
                loadStatus = remote?.loadStatus.let(loadStatusMapper)
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