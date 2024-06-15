package com.yestms.driver.android.data.mapper.auth

import com.yestms.driver.android.data.mapper.auth.common.AuthCommonMapper.dispatchersMapper
import com.yestms.driver.android.data.mapper.common.Mapper
import com.yestms.driver.android.data.mapper.common.or0
import com.yestms.driver.android.data.mapper.common.orFalse
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckResponse
import com.yestms.driver.android.data.remote.response.auth.check.AuthCheckUserRemoteModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckUserModel

object CheckMapper {

    val checkMapper:
            Mapper<AuthCheckResponse?, AuthCheckModel> =
        { remote ->
            AuthCheckModel(
                token = remote?.token.orEmpty(),
                user = remote?.user.let(checkUserMapper)
            )
        }

    private val checkUserMapper:
            Mapper<AuthCheckUserRemoteModel?, AuthCheckUserModel> =
        { remote ->
            AuthCheckUserModel(
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
                driverRate = remote?.driverRate.or0(),
                dispatcherCommission = remote?.dispatcherCommission.or0(),
                driverResident = remote?.driverResident.orEmpty(),
                currentLocation = remote?.currentLocationLat.orEmpty(),
                currentLocationLat = remote?.currentLocationLat.orEmpty(),
                currentLocationLng = remote?.currentLocationLng.orEmpty(),
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
                dispatchers = remote?.dispatchers?.map(dispatchersMapper).orEmpty()
            )
        }
}