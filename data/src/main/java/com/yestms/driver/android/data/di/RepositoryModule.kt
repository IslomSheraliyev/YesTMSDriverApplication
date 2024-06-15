package com.yestms.driver.android.data.di

import com.yestms.driver.android.data.repository.AuthRepositoryImpl
import com.yestms.driver.android.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Singleton Provides]
    fun provideGameRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository =
        repositoryImpl

}