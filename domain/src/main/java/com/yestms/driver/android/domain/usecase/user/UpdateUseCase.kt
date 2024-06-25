package com.yestms.driver.android.domain.usecase.user

import com.yestms.driver.android.domain.global.UseCaseWithTwoParams
import com.yestms.driver.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCaseWithTwoParams<Int, Boolean, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameter1: Int, parameter2: Boolean) {
        return repository.update(parameter1, parameter2)
    }
}