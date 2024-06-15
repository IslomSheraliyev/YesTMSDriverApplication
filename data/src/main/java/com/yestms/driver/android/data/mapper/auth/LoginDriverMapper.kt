package com.yestms.driver.android.data.mapper.auth.login_driver

import com.yestms.driver.android.data.mapper.common.Mapper
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.data.mapper.common.orFalse
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverCompanyRemoteModel
import com.yestms.driver.android.data.remote.response.auth.common.AuthDispatcherRemoteModel
import com.yestms.driver.android.data.remote.response.auth.common.AuthDispatchersRemoteModel
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverDriverTypeRemoteModel
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverResponse
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverUserRemoteModel
import com.yestms.driver.android.data.remote.response.auth.login_driver.AuthLoginDriverUserRoleRemoteModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverCompanyModel
import com.yestms.driver.android.domain.model.auth.common.AuthDispatcherModel
import com.yestms.driver.android.domain.model.auth.common.AuthDispatchersModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverDriverTypeModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverUserModel
import com.yestms.driver.android.domain.model.auth.login_driver.AuthLoginDriverUserRoleModel

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
                fullName = remote?.fullName.orEmpty(),
                id = remote?.id.or0(),
                email = remote?.email.orEmpty(),
                firstName = remote?.firstName.orEmpty(),
                lastName = remote?.lastName.orEmpty(),
                confirmEmail = remote?.confirmEmail.orFalse(),
                photo = remote?.photo.orEmpty(),
                dateOfBirth = remote?.dateOfBirth.orEmpty(),
                password = remote?.password.orEmpty(),
                externalId = remote?.externalId.orEmpty(),
                slug = remote?.slug.orEmpty(),
                activationLink = remote?.activationLink.orEmpty(),
                phone = remote?.phone.orEmpty(),
                driverRate = remote?.driverStatusId.or0(),
                dispatcherCommission = remote?.dispatcherCommission.or0(),
                driverResident = remote?.driverResident.orEmpty(),
                currentLocation = remote?.currentLocation.orEmpty(),
                currentLocationLat = remote?.currentLocationLat.orEmpty(),
                currentLocationLng = remote?.currentLocationLat.orEmpty(),
                note = remote?.note.orEmpty(),
                userStatus = remote?.userStatus.orFalse(),
                isAvailable = remote?.isAvailable.orFalse(),
                isOnDuty = remote?.isOnDuty.orFalse(),
                createdAt = remote?.createdAt.orEmpty(),
                updatedAt = remote?.updatedAt.orEmpty(),
                userRoleId = remote?.userRoleId.or0(),
                driverStatusId = remote?.driverStatusId.or0(),
                driverTypeId = remote?.driverTypeId.or0(),
                truckId = remote?.truckId.or0(),
                companyId = remote?.companyId.or0(),
                userRole = remote?.userRole.let(userRoleMapper),
                driverType = remote?.driverType.let(driverTypeMapper),
                dispatchers = remote?.dispatchers?.map(dispatchersMapper).orEmpty(),
                company = remote?.company.let(companyMapper)

            )
        }

    private val userRoleMapper:
            Mapper<AuthLoginDriverUserRoleRemoteModel?, AuthLoginDriverUserRoleModel> =
        { remote ->
            AuthLoginDriverUserRoleModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                description = remote?.description.orEmpty()
            )
        }

    private val driverTypeMapper:
            Mapper<AuthLoginDriverDriverTypeRemoteModel?, AuthLoginDriverDriverTypeModel> =
        { remote ->
            AuthLoginDriverDriverTypeModel(
                id = remote?.id.or0(),
                name = remote?.name.orEmpty(),
                description = remote?.description.orEmpty()
            )
        }

    private val dispatchersMapper:
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

    private val companyMapper:
            Mapper<AuthLoginDriverCompanyRemoteModel?, AuthLoginDriverCompanyModel> =
        { remote ->
            AuthLoginDriverCompanyModel(
                id = remote?.id.or0(),
                status = remote?.status.orFalse(),
                deletedAt = remote?.deletedAt.orEmpty()
            )
        }
}