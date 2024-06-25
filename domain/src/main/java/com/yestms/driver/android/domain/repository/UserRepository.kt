package com.yestms.driver.android.domain.repository

import com.yestms.driver.android.domain.model.user.DriverStatsModel

interface UserRepository {

    suspend fun getDriverStats(id: Int, period: String): DriverStatsModel

    suspend fun update(id: Int, isOnDuty: Boolean)
}