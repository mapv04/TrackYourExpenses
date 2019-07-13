package com.miguel.android.trackyourexpenses.api.retrofit

import com.miguel.android.trackyourexpenses.api.request.RequestAccount
import com.miguel.android.trackyourexpenses.api.response.ResponseAccount
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthExpenseService {
    @GET("api/user/accounts/allAccounts")
    fun getAllAccounts(): Call<List<ResponseAccount>>

    @POST("api/user/accounts/newAccount")
    fun createNewAccount(@Body requestAccounts: RequestAccount): Call<ResponseAccount>
}