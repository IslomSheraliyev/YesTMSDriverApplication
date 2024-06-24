package com.yestms.driver.android.domain.usecase.user

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.model.user.DriverStatsModel
import com.yestms.driver.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetDriverDetailsUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCaseWithTwoParams<Int, String, DriverStatsModel>(Dispatchers.IO) {
    override suspend fun execute(parameter1: Int, parameter2: String): DriverStatsModel {
        return repository.getDriverStats(parameter1, parameter2)
    }
}