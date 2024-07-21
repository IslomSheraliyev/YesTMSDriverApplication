package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithThreeParams
import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SocketSendNoticeUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithThreeParams<List<Int>, String, String, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: List<Int>, parameter2: String, parameter3: String) {
        repository.sendIncident(
            event = "sendNotice",
            dispatchers = parameter1,
            titleNotice = parameter2,
            description = parameter3
        )
    }
}