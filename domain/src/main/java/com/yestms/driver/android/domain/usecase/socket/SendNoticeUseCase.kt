package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SendNoticeUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithTwoParams<List<Int>, String, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: List<Int>, parameter2: String) {
        repository.sendNotice(
            event = "sendNotice",
            dispatchers = parameter1,
            titleNotice = parameter2
        )
    }
}