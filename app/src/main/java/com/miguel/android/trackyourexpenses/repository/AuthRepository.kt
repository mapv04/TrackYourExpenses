package com.miguel.android.trackyourexpenses.repository

import android.util.Log
import com.miguel.android.trackyourexpenses.common.PREF_NAME
import com.miguel.android.trackyourexpenses.common.PREF_TOKEN
import com.miguel.android.trackyourexpenses.common.PREF_USERNAME
import com.miguel.android.trackyourexpenses.common.SharedPreferencesManager
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthRepository {

    private val expensesService: ExpensesService
    private val expensesClient: ExpensesClient = ExpensesClient.instance

    init{
        expensesService = expensesClient.expensesService
    }

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
            Log.e("AuthRepository", "check network connection")
            return 99 // request Failure
        }
    }

    suspend fun register(requestSignUp: RequestSignUp): Int{
        return try{
            val response = expensesService.doSignUp(requestSignUp)
            response.code()

        }catch(e: Exception){
            Log.e("AuthRepository", "error: $e")
            99 // request Failure
        }
    }
}