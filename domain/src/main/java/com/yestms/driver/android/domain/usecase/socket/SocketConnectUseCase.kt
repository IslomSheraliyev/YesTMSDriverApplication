package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SocketConnectUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCase<Unit>(Dispatchers.IO) {
    override suspend fun execute() {
        repository.connect()
    }
}