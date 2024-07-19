package com.yestms.driver.android.data.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.yestms.driver.android.data.BuildConfig
import com.yestms.driver.android.data.remote.interceptors.JsonParseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIME_OUT = 20L
    private const val BASE_URL = "https://odmin.yes-tms.com/api/"

    @[Singleton Provides]
    fun provideGson() = Gson()

    @[Singleton Provides]
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @[Singleton Provides]
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiFactory)
            .build()
    }

    @[Singleton Provides]
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT * 3, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(JsonParseInterceptor(context))

//        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor {
                Log.d("RRR", "provideOkhttpClient: $it")
            }
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
            clientBuilder.addInterceptor(
                ChuckerInterceptor(context)
            )
//        }
        return clientBuilder.build()
    }
}