package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.repository.SocketRepository
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RenderDispatcherDashboardUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithParams<Emitter.Listener, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter: Emitter.Listener) {
        repository.renderDispatcherDashboard(
            event = "renderDispatcherDashboard",
            listener = parameter
        )
    }
}