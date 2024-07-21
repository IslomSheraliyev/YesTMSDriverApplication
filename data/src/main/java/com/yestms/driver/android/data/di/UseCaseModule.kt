package com.yestms.driver.android.data.di

import com.yestms.driver.android.domain.repository.AuthRepository
import com.yestms.driver.android.domain.repository.LoadsRepository
import com.yestms.driver.android.domain.repository.NotificationsRepository
import com.yestms.driver.android.domain.repository.SocketRepository
import com.yestms.driver.android.domain.repository.UserRepository
import com.yestms.driver.android.domain.usecase.auth.AuthCheckUseCase
import com.yestms.driver.android.domain.usecase.auth.AuthLoginDriverUseCase
import com.yestms.driver.android.domain.usecase.loads.GetAlertStatusesUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadUseCase
import com.yestms.driver.android.domain.usecase.loads.GetLoadsUseCase
import com.yestms.driver.android.domain.usecase.loads.ReportProblemUseCase
import com.yestms.driver.android.domain.usecase.loads.UpdateLoadStatusUseCase
import com.yestms.driver.android.domain.usecase.loads.UploadImagesUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationUseCase
import com.yestms.driver.android.domain.usecase.notifications.DeleteNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetNotificationsUseCase
import com.yestms.driver.android.domain.usecase.notifications.GetUnreadCountUseCase
import com.yestms.driver.android.domain.usecase.notifications.ViewNotificationUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketAddUserUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketChangeForDashboardUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketConnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketDisconnectUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketKickUserUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketRenderDispatcherDashboardUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketSendNoticeUseCase
import com.yestms.driver.android.domain.usecase.user.GetDriverDetailsUseCase
import com.yestms.driver.android.domain.usecase.user.UpdateUseCase
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
    fun provideAuthCheckUseCase(repository: AuthRepository) = AuthCheckUseCase(repository)

    @[Singleton Provides]
    fun provideGetLoadsUseCase(repository: LoadsRepository) = GetLoadsUseCase(repository)

    @[Singleton Provides]
    fun provideGetLoadUseCase(repository: LoadsRepository) = GetLoadUseCase(repository)

    @[Singleton Provides]
    fun provideUpdateLoadStatusUseCase(repository: LoadsRepository) =
        UpdateLoadStatusUseCase(repository)

    @[Singleton Provides]
    fun provideGetAlertStatusesUseCase(repository: LoadsRepository) =
        GetAlertStatusesUseCase(repository)

    @[Singleton Provides]
    fun provideReportProblemUseCase(repository: LoadsRepository) =
        ReportProblemUseCase(repository)

    @[Singleton Provides]
    fun provideUploadImagesUseCase(repository: LoadsRepository) =
        UploadImagesUseCase(repository)


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

    @[Singleton Provides]
    fun provideUpdateUseCase(repository: UserRepository) =
        UpdateUseCase(repository)

    @[Singleton Provides]
    fun provideConnectSocketUseCase(repository: SocketRepository) =
        SocketConnectUseCase(repository)

    @[Singleton Provides]
    fun provideDisconnectSocketUseCase(repository: SocketRepository) =
        SocketDisconnectUseCase(repository)


    @[Singleton Provides]
    fun provideAddUserUseCase(repository: SocketRepository) =
        SocketAddUserUseCase(repository)

    @[Singleton Provides]
    fun provideKickUserUseCase(repository: SocketRepository) =
        SocketKickUserUseCase(repository)

    @[Singleton Provides]
    fun provideChangeForDashboardUseCase(repository: SocketRepository) =
        SocketChangeForDashboardUseCase(repository)

    @[Singleton Provides]
    fun provideSendNoticeUseCase(repository: SocketRepository) =
        SocketSendNoticeUseCase(repository)

    @[Singleton Provides]
    fun provideRenderDispatcherDashboard(repository: SocketRepository) =
        SocketRenderDispatcherDashboardUseCase(repository)


}