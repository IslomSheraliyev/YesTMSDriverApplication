package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.loads.AlertStatusesRemoteItemModel
import com.yestms.driver.android.data.remote.response.loads.DispatcherAssignedRemoteModel
import com.yestms.driver.android.data.remote.response.loads.DispatcherRemoteModel
import com.yestms.driver.android.data.remote.response.loads.LoadAlertLogRemoteMode
import com.yestms.driver.android.data.remote.response.loads.LoadAlertLogsRemoteModel
import com.yestms.driver.android.domain.enums.DriverDetailsLoadStatus
import com.yestms.driver.android.data.remote.response.loads.LoadResponse
import com.yestms.driver.android.data.remote.response.loads.LoadStatusLogsRemoteItem
import com.yestms.driver.android.data.remote.response.loads.LoadStatusLogsRemoteItemLoadStatus
import com.yestms.driver.android.data.remote.response.loads.LoadsItemRemoteModel
import com.yestms.driver.android.data.remote.response.loads.LoadStatusRemoteModel
import com.yestms.driver.android.data.remote.response.loads.MediaBOLRemoteModel
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.model.loads.DispatcherAssignedModel
import com.yestms.driver.android.domain.model.loads.DispatcherModel
import com.yestms.driver.android.domain.model.loads.LoadAlertStatusModel
import com.yestms.driver.android.domain.model.loads.LoadAlertsLogsItemModel
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.model.loads.LoadStatusLogsItem
import com.yestms.driver.android.domain.model.loads.LoadStatusLogsItemLoadStatus
import com.yestms.driver.android.domain.model.loads.LoadsItemModel
import com.yestms.driver.android.domain.model.loads.LoadStatusModel
import com.yestms.driver.android.domain.model.loads.MediaBOLModel

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
                commodity = remote?.commodity.orEmpty(),
                weight = remote?.weight.orEmpty(),
                height = remote?.height.orEmpty(),
                length = remote?.length.orEmpty(),
                notes = remote?.notes.orEmpty(),
                pickUpNote = remote?.pickUpNote.orEmpty(),
                pickUpLocation = remote?.pickUpLocation.orEmpty(),
                pickUpPolitical = remote?.pickUpPolitical.orEmpty(),
                deliveryNote = remote?.deliveryNote.orEmpty(),
                deliveryLocation = remote?.deliveryLocation.orEmpty(),
                deliveryPolitical = remote?.deliveryPolitical.orEmpty(),
                loadStatus = remote?.loadStatus.let(loadStatusMapper),
                driverId = remote?.driverId.or0(),
                dispatcherId = remote?.dispatcherId.or0(),
                loadAlertLogs = remote?.loadAlertsLogs?.map(loadAlertLogsMapper).orEmpty(),
                loadStatusLogs = remote?.loadStatusLogs?.map(loadStatusLogsMapper).orEmpty(),
                dispatchers = remote?.dispatchers?.map(dispatcherMapper).orEmpty(),
                mediaBOLModels = remote?.mediaBOLs?.map(mediaBOLModelMapper).orEmpty()
            )
        }

    val loadAlertStatusesMapper: Mapper<AlertStatusesRemoteItemModel?, AlertStatusesItemModel> =
        { remote ->
            AlertStatusesItemModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty()
            )
        }

    private val loadStatusLogsMapper: Mapper<LoadStatusLogsRemoteItem?, LoadStatusLogsItem> =
        { remote ->
            LoadStatusLogsItem(
                createdAt = remote?.createdAt.orEmpty(),
                loadStatusLogsItemLoadStatus = remote?.loadStatusLogsItemLoadStatus.let(
                    loadStatusLogsItemLoadStatusMapper
                )
            )
        }

    private val loadStatusLogsItemLoadStatusMapper: Mapper<LoadStatusLogsRemoteItemLoadStatus?, LoadStatusLogsItemLoadStatus> =
        { remote ->
            LoadStatusLogsItemLoadStatus(
                name = remote?.name.orEmpty()
            )
        }

    private val loadStatusMapper:
            Mapper<LoadStatusRemoteModel?, LoadStatusModel> =
        { remote ->
            LoadStatusModel(
                id = remote?.id.or0(),
                name = DriverDetailsLoadStatus.getStatus(remote?.name.orEmpty()),
                color = remote?.color.orEmpty()
            )
        }

    private val loadAlertLogsMapper: Mapper<LoadAlertLogsRemoteModel?, LoadAlertsLogsItemModel> =
        { remote ->
            LoadAlertsLogsItemModel(
                createdAt = remote?.createdAt.orEmpty(),
                id = remote?.id.or0(),
                loadAlertStatus = remote?.loadAlertStatus.let(loadAlertStatusMapper)
            )
        }

    private val loadAlertStatusMapper: Mapper<LoadAlertLogRemoteMode?, LoadAlertStatusModel> =
        { remote ->
            LoadAlertStatusModel(
                name = remote?.name.orEmpty()
            )
        }

    private val dispatcherMapper: Mapper<DispatcherRemoteModel?, DispatcherModel> =
        { remote ->
            DispatcherModel(
                id = remote?.id.or0(),
                dispatcherAssigned = remote?.dispatcherAssigned.let(dispatcherAssignedMapper)
            )
        }

    private val dispatcherAssignedMapper: Mapper<DispatcherAssignedRemoteModel?, DispatcherAssignedModel> =
        { remote ->
            DispatcherAssignedModel(
                id = remote?.id.or0(),
                userId = remote?.userId.or0(),
                dispatcherId = remote?.dispatcherId.or0()

            )
        }

    private val mediaBOLModelMapper: Mapper<MediaBOLRemoteModel?, MediaBOLModel> =
        { remote ->
            MediaBOLModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                loadId = remote?.loadId.or0()
            )
        }
}