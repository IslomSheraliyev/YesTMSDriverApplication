package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SocketKickUserUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithParams<Int, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter: Int) {
        repository.kickUser(
            event = "kick",
            userId = parameter
        )
    }
}