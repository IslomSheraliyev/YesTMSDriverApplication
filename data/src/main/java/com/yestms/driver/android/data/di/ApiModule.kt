package com.yestms.driver.android.data.di

import com.yestms.driver.android.data.remote.api.AuthApi
import com.yestms.driver.android.data.remote.api.LoadsApi
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

}