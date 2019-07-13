package com.miguel.android.trackyourexpenses.api.retrofit

import com.miguel.android.trackyourexpenses.utils.API_EXPENSES_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ExpensesClient{
    val instance: ExpensesClient = ExpensesClient
    private val expensesService: ExpensesService
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_EXPENSES_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init{
        expensesService = retrofit.create(ExpensesService::class.java)
    }
}