package com.miguel.android.trackyourexpenses.data.api.retrofit

import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.data.api.response.ResponseAuth
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpensesService {
    @POST("api/user/auth/login")
    suspend fun doLogin(@Body requestLogin: RequestLogin): Response<ResponseAuth>
    @POST("api/user/auth/register")
    suspend fun doSignUp(@Body requestSignUp: RequestSignUp): Response<ResponseAuth>
}