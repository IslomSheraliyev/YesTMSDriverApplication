package com.yestms.driver.android.domain.model.auth.check

import com.yestms.driver.android.domain.model.auth.common.AuthDispatchersModel


data class AuthCheckUserModel(
    val fullName: String,
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val confirmEmail: Boolean,
    val photo: String,
    val dateOfBirth: String,
    val password: String,
    val externalId: String,
    val slug: String,
    val activationLink: String,
    val phone: String,
    val driverRate: Int,
    val dispatcherCommission: Int,
    val driverResident: String,
    val currentLocation: String,
    val currentLocationLat: String,
    val currentLocationLng: String,
    val note: String,
    val userStatus: Boolean,
    val isAvailable: Boolean,
    val isOnDuty: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val userRoleId: Int,
    val driverStatusId: Int,
    val driverTypeId: Int,
    val truckId: Int,
    val companyId: Int,
    val dispatchers: List<AuthDispatchersModel>
)