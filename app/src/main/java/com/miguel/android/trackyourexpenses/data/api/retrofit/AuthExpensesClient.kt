package com.miguel.android.trackyourexpenses.data.api.retrofit

import com.miguel.android.trackyourexpenses.common.API_EXPENSES_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthExpensesClient {
    val instance: AuthExpensesClient = AuthExpensesClient
    val expensesService: AuthExpensesService
    private val okHttpClientBuilder = OkHttpClient.Builder()
    private val client: OkHttpClient

    init{
        okHttpClientBuilder.addInterceptor(AuthInterceptor())
        client = okHttpClientBuilder.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_EXPENSES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        expensesService = retrofit.create(AuthExpensesService::class.java)
    }

}