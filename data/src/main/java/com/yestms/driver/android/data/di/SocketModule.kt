package com.yestms.driver.android.data.di

import com.yestms.driver.android.data.remote.socket.SocketManager
import com.yestms.driver.android.data.repository.SocketRepositoryImpl
import com.yestms.driver.android.domain.repository.SocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @[Provides Singleton]
    fun provideSocketManager(): SocketManager = SocketManager()

    @[Provides Singleton]
    fun provideSocketRepository(socketManager: SocketManager): SocketRepository =
        SocketRepositoryImpl(socketManager)


}