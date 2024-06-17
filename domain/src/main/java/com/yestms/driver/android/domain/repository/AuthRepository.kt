package com.yestms.driver.android.domain.repository

import com.yestms.driver.android.domain.model.auth.AuthLoginDriverModel

interface AuthRepository {

    suspend fun loginDriver(externalId: String): AuthLoginDriverModel

    suspend fun check()
}