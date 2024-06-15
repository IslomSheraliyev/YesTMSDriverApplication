package com.yestms.driver.android.data.remote.interceptors

import android.content.Context
import com.yestms.driver.android.data.local.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

class JsonParseInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = AppPreferences.accessToken

        val request = chain.request().newBuilder()
        request.addHeader("accept", "application/json")
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}