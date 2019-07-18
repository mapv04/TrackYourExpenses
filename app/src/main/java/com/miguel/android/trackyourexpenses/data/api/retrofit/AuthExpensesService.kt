package com.miguel.android.trackyourexpenses.data.api.retrofit

import com.miguel.android.trackyourexpenses.data.api.request.RequestAccount
import com.miguel.android.trackyourexpenses.data.api.response.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthExpensesService {
    @GET("api/user/accounts/allAccounts")
    fun getAllAccounts(): Call<List<Account>>

    @POST("api/user/accounts/newAccount")
    fun createNewAccount(@Body requestAccounts: RequestAccount): Call<Account>

    @DELETE("api/user/accounts/deleteAccount")
    fun deleteAccount(@Body requestAccount: RequestAccount)
}