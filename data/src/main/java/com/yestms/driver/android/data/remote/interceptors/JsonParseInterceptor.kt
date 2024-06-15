package uz.fitgroup.gameportal.data.remote.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import uz.fitgroup.gameportal.data.local.AppPreferences

class JsonParseInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {


        val token = AppPreferences.accessToken

        val request = chain.request().newBuilder()
        request.addHeader("accept", "application/json")
        request.addHeader("Content-Type", "application/json")
        request.addHeader("Accept-Language", AppPreferences.locale)
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }

}