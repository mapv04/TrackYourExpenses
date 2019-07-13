package com.miguel.android.trackyourexpenses.api.retrofit

import com.miguel.android.trackyourexpenses.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.api.response.ResponseAuth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpensesService {
    @POST("api/users/auth/login")
    fun doLogin(@Body requestLogin: RequestLogin): Call<ResponseAuth>
    @POST("api/user/auth/register")
    fun doSignUp(@Body requestSignUp: RequestSignUp): Call<ResponseAuth>
}