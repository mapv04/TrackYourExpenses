package com.miguel.android.trackyourexpenses.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.common.PREF_NAME
import com.miguel.android.trackyourexpenses.common.PREF_TOKEN
import com.miguel.android.trackyourexpenses.common.PREF_USERNAME
import com.miguel.android.trackyourexpenses.common.SharedPreferencesManager
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.data.api.response.ResponseAuth
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AuthRepository {

    private val expensesService: ExpensesService
    private val expensesClient: ExpensesClient = ExpensesClient.instance

    init{
        expensesService = expensesClient.expensesService
    }

    /*fun login(requestLogin: RequestLogin): Int {
        var responseLogin = 0
        val call = expensesService.doLogin(requestLogin)
        call.enqueue(object : Callback<ResponseAuth> {
            override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                if (response.isSuccessful) {
                    SharedPreferencesManager.setSomeStringValue(PREF_TOKEN, response.body()?.token.toString())
                    SharedPreferencesManager.setSomeStringValue(
                        PREF_NAME,
                        response.body()?.name.toString() + " " + response.body()?.lastname.toString()
                    )
                    SharedPreferencesManager.setSomeStringValue(PREF_USERNAME, response.body()?.username.toString())
                } else {
                    Log.i("LoginRepository","response unsuccessful")
                }
                responseLogin = response.code()
            }

            override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                Log.e("AuthRepository", "Exception: ${t}")
                responseLogin = 3 // check internet connection
            }

        })
        return responseLogin
    }*/

    suspend fun login(requestLogin: RequestLogin): Int {
        try {
            val response = expensesService.doLogin(requestLogin)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    SharedPreferencesManager.setSomeStringValue(PREF_TOKEN, response.body()?.token.toString())
                    SharedPreferencesManager.setSomeStringValue(
                        PREF_NAME,
                        response.body()?.name.toString() + " " + response.body()?.lastname.toString()
                    )
                    SharedPreferencesManager.setSomeStringValue(PREF_USERNAME, response.body()?.username.toString())
                } else {
                    Log.i("LoginRepository", "response unsuccessful")
                }
            }
            return response.code()
        }catch (e: Exception){
            return 3 // request Failure
        }
    }
}