package com.yestms.driver.android.data.di

import com.yestms.driver.android.domain.repository.AuthRepository
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @[Singleton Provides]
    fun provideAuthLoginDriverUseCase(repository: AuthRepository) = AuthLoginDriverUseCase(repository)

    @[Singleton Provides]
    fun provideAuthCheckUseCase(repository: AuthRepository) = AuthCheckUseCase(repository)

}