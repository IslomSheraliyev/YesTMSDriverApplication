package com.yestms.driver.android.data.di

import com.yestms.driver.android.domain.repository.AuthRepository
import com.yestms.driver.android.domain.repository.LoadsRepository
import com.yestms.driver.android.domain.repository.NotificationsRepository
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteAllNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCount
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @[Singleton Provides]
    fun provideAuthLoginDriverUseCase(repository: AuthRepository) =
        AuthLoginDriverUseCase(repository)

    @[Singleton Provides]
    fun provideAuthCheckUseCase(repository: LoadsRepository) = GetLoadsUseCase(repository)

    @[Singleton Provides]
    fun provideGetLoadUseCase(repository: LoadsRepository) = GetLoadUseCase(repository)

    @[Singleton Provides]
    fun provideGetLoadsUseCase(repository: AuthRepository) = AuthCheckUseCase(repository)

    @[Singleton Provides]
    fun provideGetNoticesUseCase(repository: NotificationsRepository) =
        GetNotificationsUseCase(repository)

    @[Singleton Provides]
    fun provideGetUnreadCountUseCount(repository: NotificationsRepository) =
        GetUnreadCountUseCount(repository)

    @[Singleton Provides]
    fun provideDeleteAllNotifications(repository: NotificationsRepository) =
        DeleteAllNotificationsUseCase(repository)


}