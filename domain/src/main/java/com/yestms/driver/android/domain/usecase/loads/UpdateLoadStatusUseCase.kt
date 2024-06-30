package com.yestms.driver.android.domain.usecase.loads

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateLoadStatusUseCase @Inject constructor(
    private val repository: LoadsRepository
) : UseCaseWithTwoParams<Int, Int, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: Int, parameter2: Int) {
        repository.updateLoadStatus(parameter1, parameter2)
    }
}