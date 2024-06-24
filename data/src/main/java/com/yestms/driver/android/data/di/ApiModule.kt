package com.yestms.driver.android.data.di

import com.yestms.driver.android.data.remote.api.AuthApi
import com.yestms.driver.android.data.remote.api.LoadsApi
import com.yestms.driver.android.data.remote.api.NotificationsApi
import com.yestms.driver.android.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @[Provides Singleton]
    fun provideLoadsApi(retrofit: Retrofit): LoadsApi {
        return retrofit.create(LoadsApi::class.java)
    }

    @[Provides Singleton]
    fun provideNoticesApi(retrofit: Retrofit): NotificationsApi {
        return retrofit.create(NotificationsApi::class.java)
    }

    @[Provides Singleton]
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }


}