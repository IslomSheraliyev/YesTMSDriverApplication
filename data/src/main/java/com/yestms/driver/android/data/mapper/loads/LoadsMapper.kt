package com.yestms.driver.android.data.mapper.loads

import com.yestms.driver.android.data.mapper.common.Mapper
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.data.mapper.common.orFalse
import com.yestms.driver.android.data.remote.response.loads.get.GetLoadsResponse
import com.yestms.driver.android.data.remote.response.loads.get.LoadRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadStatusRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsBrokerRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsCompanyMcNumberRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsDispatcherRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsDriverLoadsRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsDriverRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsDriverTypeRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsMediaBolsRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsRateConsRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsTruckRemoteModel
import com.yestms.driver.android.data.remote.response.loads.get.LoadsTruckTypeRemoteModel
import com.yestms.driver.android.domain.model.loads.get.GetLoadsModel
import com.yestms.driver.android.domain.model.loads.get.LoadModel
import com.yestms.driver.android.domain.model.loads.get.LoadStatusModel
import com.yestms.driver.android.domain.model.loads.get.LoadsBrokerModel
import com.yestms.driver.android.domain.model.loads.get.LoadsCompanyMcNumberModel
import com.yestms.driver.android.domain.model.loads.get.LoadsDispatcherModel
import com.yestms.driver.android.domain.model.loads.get.LoadsDriverLoadsModel
import com.yestms.driver.android.domain.model.loads.get.LoadsDriverModel
import com.yestms.driver.android.domain.model.loads.get.LoadsDriverTypeModel
import com.yestms.driver.android.domain.model.loads.get.LoadsMediaBolsModel
import com.yestms.driver.android.domain.model.loads.get.LoadsRateConsModel
import com.yestms.driver.android.domain.model.loads.get.LoadsTruckModel
import com.yestms.driver.android.domain.model.loads.get.LoadsTruckTypeModel

object LoadsMapper {

    val loadsMapper:
            Mapper<GetLoadsResponse?, GetLoadsModel> =
        { remote ->
            GetLoadsModel(
                rows = remote?.rows?.map(loadMapper).orEmpty(),
                count = remote?.count.or0()
            )
        }

     val loadMapper:
            Mapper<LoadRemoteModel?, LoadModel> =
        { remote ->
            LoadModel(
                id = remote?.id.or0(),
                loadId = remote?.loadId.orEmpty(),
                activationLink = remote?.activationLink.orEmpty(),
                rate = remote?.rate.or0(),
                mileage = remote?.mileage.or0(),
                commodity = remote?.commodity.orEmpty(),
                weight = remote?.weight.or0(),
                height = remote?.height.or0(),
                length = remote?.length.or0(),
                pickUpLocation = remote?.pickUpLocation.orEmpty(),
                pickUpLat = remote?.pickUpLat.orEmpty(),
                pickUpLng = remote?.pickUpLng.orEmpty(),
                pickUpPolitical = remote?.pickUpPolitical.orEmpty(),
                pickUpDate = remote?.pickUpDate.orEmpty(),
                pickUpAppointment = remote?.pickUpAppointment.orFalse(),
                pickUpNote = remote?.pickUpNote.orEmpty(),
                deliveryLocation = remote?.deliveryLocation.orEmpty(),
                deliveryLat = remote?.deliveryLat.orEmpty(),
                deliveryLng = remote?.deliveryLng.orEmpty(),
                deliveryPolitical = remote?.deliveryPolitical.orEmpty(),
                deliveryDate = remote?.deliveryDate.orEmpty(),
                deliveryAppointment = remote?.deliveryAppointment.orFalse(),
                deliveryNote = remote?.deliveryNote.orEmpty(),
                expenses = remote?.expenses.or0(),
                notes = remote?.notes.orEmpty(),
                createdAt = remote?.createdAt.orEmpty(),
                updatedAt = remote?.updatedAt.orEmpty(),
                isAlert = remote?.isAlert.orFalse(),
                loadStatusId = remote?.loadStatusId.or0(),
                mcId = remote?.mcId.or0(),
                brokerId = remote?.brokerId.or0(),
                driverId = remote?.driverId.or0(),
                dispatcherId = remote?.dispatcherId.or0(),
                parentId = remote?.parentId.or0(),
                companyId = remote?.companyId.or0(),
                loadStatus = remote?.loadStatus.let(loadStatusMapper),
                companyMcNumber = remote?.companyMcNumber.let(loadsCompanyMcNumberMapper),
                broker = remote?.broker.let(brokerMapper),
                drivers = remote?.drivers?.map(driversMapper).orEmpty(),
                dispatcher = remote?.dispatcher.let(dispatcherMapper),
                rateCons = remote?.rateCons?.map(rateConsMapper).orEmpty(),
                mediaBols = remote?.mediaBols?.map(mediaBolsMapper).orEmpty()
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

    private val loadsCompanyMcNumberMapper:
            Mapper<LoadsCompanyMcNumberRemoteModel?, LoadsCompanyMcNumberModel> =
        { remote ->
            LoadsCompanyMcNumberModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                companyId = remote?.companyId.orEmpty()
            )
        }

    private val brokerMapper:
            Mapper<LoadsBrokerRemoteModel?, LoadsBrokerModel> =
        { remote ->
            LoadsBrokerModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty()
            )
        }

    private val driversMapper:
            Mapper<LoadsDriverRemoteModel?, LoadsDriverModel> =
        { remote ->
            LoadsDriverModel(
                fullName = remote?.fullName.orEmpty(),
                id = remote?.id.or0(),
                firstName = remote?.firstName.orEmpty(),
                lastName = remote?.lastName.orEmpty(),
                truckId = remote?.truckId.or0(),
                driverRate = remote?.driverRate.or0(),
                driverType = remote?.driverType.let(driverTypeMapper),
                truck = remote?.truck.let(truckMapper),
                driverLoads = remote?.driverLoads.let(driverLoadsMapper)
            )
        }

    private val driverTypeMapper:
            Mapper<LoadsDriverTypeRemoteModel?, LoadsDriverTypeModel> =
        { remote ->
            LoadsDriverTypeModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                description = remote?.description.orEmpty()
            )
        }

    private val truckMapper:
            Mapper<LoadsTruckRemoteModel?, LoadsTruckModel> =
        { remote ->
            LoadsTruckModel(
                id = remote?.id.or0(),
                truckType = remote?.truckType.let(truckTypeMapper)
            )
        }

    private val truckTypeMapper:
            Mapper<LoadsTruckTypeRemoteModel?, LoadsTruckTypeModel> =
        { remote ->
            LoadsTruckTypeModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty()
            )
        }

    private val driverLoadsMapper:
            Mapper<LoadsDriverLoadsRemoteModel?, LoadsDriverLoadsModel> =
        { remote ->
            LoadsDriverLoadsModel(
                id = remote?.id.or0(),
                userId = remote?.userId.or0(),
                loadId = remote?.loadId.or0()
            )
        }

    private val dispatcherMapper:
            Mapper<LoadsDispatcherRemoteModel?, LoadsDispatcherModel> =
        { remote ->
            LoadsDispatcherModel(
                fullName = remote?.fullName.orEmpty(),
                id = remote?.id.or0(),
                firstName = remote?.firstName.orEmpty(),
                lastName = remote?.lastName.orEmpty()
            )
        }

    private val rateConsMapper:
            Mapper<LoadsRateConsRemoteModel?, LoadsRateConsModel> =
        { remote ->
            LoadsRateConsModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                loadId = remote?.loadId.or0()
            )
        }

    private val mediaBolsMapper:
            Mapper<LoadsMediaBolsRemoteModel?, LoadsMediaBolsModel> =
        { remote ->
            LoadsMediaBolsModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                loadId = remote?.loadId.or0(),
            )
        }
}