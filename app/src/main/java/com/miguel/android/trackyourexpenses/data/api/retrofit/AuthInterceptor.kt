package com.miguel.android.trackyourexpenses.data.api.retrofit

import android.util.Log
import com.miguel.android.trackyourexpenses.common.PREF_TOKEN
import com.miguel.android.trackyourexpenses.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPreferencesManager.getSomeStringValue(PREF_TOKEN)
        val request = chain.request().newBuilder().addHeader("auth-token", token).build()
        return chain.proceed(request)
    }
}