package com.yestms.driver.android.data.di

import com.yestms.driver.android.domain.repository.AuthRepository
import com.yestms.driver.android.domain.repository.LoadsRepository
import com.yestms.driver.android.domain.repository.NotificationsRepository
import com.yestms.driver.android.domain.repository.UserRepository
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCase
import com.yestms.driver.android.domain.usecase.notifications.ViewNotificationUseCase
import com.yestms.driver.android.domain.usecase.user.GetDriverDetailsUseCase
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
    fun provideGetUnreadCountUseCase(repository: NotificationsRepository) =
        GetUnreadCountUseCase(repository)

    @[Singleton Provides]
    fun provideDeleteNotificationsUseCase(repository: NotificationsRepository) =
        DeleteNotificationsUseCase(repository)

    @[Singleton Provides]
    fun provideDeleteNotificationUseCase(repository: NotificationsRepository) =
        DeleteNotificationUseCase(repository)

    @[Singleton Provides]
    fun provideViewNotificationUseCase(repository: NotificationsRepository) =
        ViewNotificationUseCase(repository)

    @[Singleton Provides]
    fun provideGetDriverDetails(repository: UserRepository) =
        GetDriverDetailsUseCase(repository)

}