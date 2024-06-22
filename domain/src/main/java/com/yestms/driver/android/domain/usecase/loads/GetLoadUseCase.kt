package com.yestms.driver.android.domain.usecase.loads

import com.yestms.driver.android.domain.global.UseCaseWithParams
import com.yestms.driver.android.domain.model.loads.LoadModel
import com.yestms.driver.android.domain.repository.LoadsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetLoadUseCase @Inject constructor(
    private val repository: LoadsRepository
) : UseCaseWithParams<Int, LoadModel>(Dispatchers.IO) {
    override suspend fun execute(parameter: Int): LoadModel {
        return repository.getLoad(parameter)
    }
}