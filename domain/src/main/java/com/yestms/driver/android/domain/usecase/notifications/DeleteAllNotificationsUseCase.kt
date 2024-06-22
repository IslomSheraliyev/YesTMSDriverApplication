package com.yestms.driver.android.domain.usecase.notifications

import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteAllNotificationsUseCase @Inject constructor(
    private val repository: NotificationsRepository
) : UseCase<Int>(Dispatchers.IO) {
    override suspend fun execute(): Int {
        return repository.deleteAllNotifications()
    }
}