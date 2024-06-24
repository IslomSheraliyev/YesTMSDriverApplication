package com.yestms.driver.android.domain.usecase.notifications

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ViewNotificationUseCase @Inject constructor(
    private val repository: NotificationsRepository
) : UseCaseWithTwoParams<Int, Boolean, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: Int, parameter2: Boolean) {
        repository.viewNotification(parameter1, parameter2)
    }
}