package com.yestms.driver.android.data.repository

import com.yestms.driver.android.data.mapper.UserMapper
import com.yestms.driver.android.data.remote.api.UserApi
import com.yestms.driver.android.domain.model.user.DriverStatsModel
import com.yestms.driver.android.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {
    override suspend fun getDriverStats(id: Int, period: String, ): DriverStatsModel {
        return api.getDriverStats(id, period).let(UserMapper.userMapper)
    }
}