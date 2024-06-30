package com.yestms.driver.android.domain.usecase.loads

import com.yestms.driver.android.domain.global.UseCase
import com.yestms.driver.android.domain.model.loads.AlertStatusesItemModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAlertStatusesUseCase @Inject constructor(
    private val repository: LoadsRepository
) : UseCase<List<AlertStatusesItemModel>>(Dispatchers.IO) {
    override suspend fun execute(): List<AlertStatusesItemModel> {
        return repository.getAlertStatuses()
    }
}