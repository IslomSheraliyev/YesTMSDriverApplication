package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SocketChangeForDashboardUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithParams<List<Int>, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter: List<Int>) {
        repository.changeForDashboard(
            event = "changeForDashboard",
            dispatchers = parameter
        )
    }
}