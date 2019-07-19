package com.miguel.android.trackyourexpenses.data.api.retrofit

import com.miguel.android.trackyourexpenses.data.api.request.RequestAccount
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.api.response.AccountDeleted
import retrofit2.Call
import retrofit2.http.*

interface AuthExpensesService {
    @GET("api/user/accounts/allAccounts")
    fun getAllAccounts(): Call<List<Account>>

    @POST("api/user/accounts/newAccount")
    fun createNewAccount(@Body requestAccounts: RequestAccount): Call<Account>

    @HTTP(method="DELETE", path = "api/user/accounts/deleteAccount", hasBody = true)
    fun deleteAccount(@Body requestAccount: RequestAccount): Call<AccountDeleted>
}