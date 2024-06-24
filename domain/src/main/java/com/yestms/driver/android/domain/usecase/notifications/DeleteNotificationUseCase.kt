package com.yestms.driver.android.domain.usecase.notifications


import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteNotificationUseCase @Inject constructor(
    private val repository: NotificationsRepository
) : UseCaseWithParams<Int, Int>(Dispatchers.IO) {
    override suspend fun execute(parameter: Int): Int {
        return repository.deleteNotification(parameter)
    }
}