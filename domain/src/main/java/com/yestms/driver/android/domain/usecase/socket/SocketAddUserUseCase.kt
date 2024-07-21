package com.yestms.driver.android.domain.usecase.socket

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.model.socket.AddUserSocketModel
import com.yestms.driver.android.domain.repository.SocketRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SocketAddUserUseCase @Inject constructor(
    private val repository: SocketRepository
) : UseCaseWithTwoParams<Int, Int, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: Int, parameter2: Int) {
        repository.addUser(
            event = "addUser",
            model = AddUserSocketModel(
                userId = parameter1,
                role = parameter2
            )
        )
    }
}
