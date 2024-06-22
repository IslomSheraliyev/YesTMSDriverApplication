package com.yestms.driver.android.data.di

import com.yestms.driver.android.data.repository.AuthRepositoryImpl
import com.yestms.driver.android.data.repository.LoadsRepositoryImpl
import com.yestms.driver.android.data.repository.NotificationsRepositoryImpl
import com.yestms.driver.android.domain.repository.AuthRepository
import com.yestms.driver.android.domain.repository.LoadsRepository
import com.yestms.driver.android.domain.repository.NotificationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Singleton Provides]
    fun provideAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository =
        repositoryImpl

    @[Singleton Provides]
    fun provideLoadsRepository(repositoryImpl: LoadsRepositoryImpl): LoadsRepository =
        repositoryImpl

    @[Singleton Provides]
    fun provideNoticesRepository(repositoryImpl: NotificationsRepositoryImpl): NotificationsRepository =
        repositoryImpl


}