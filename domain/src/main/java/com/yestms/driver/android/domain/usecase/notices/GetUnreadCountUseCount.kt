package com.yestms.driver.android.domain.usecase.notices

import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.repository.NoticesRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetUnreadCountUseCount @Inject constructor(
    private val repository: NoticesRepository
) : UseCase<Int>(Dispatchers.IO) {
    override suspend fun execute(): Int {
        return repository.getUnreadCount()
    }
}