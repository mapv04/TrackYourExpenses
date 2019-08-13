package com.miguel.android.trackyourexpenses.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovementRepository {

    private val authExpenseService: AuthExpensesService
    private val authExpensesClient: AuthExpensesClient = AuthExpensesClient.instance

    init {
        authExpenseService = authExpensesClient.expensesService
    }



    suspend fun getIncome(income: String): Movements?{
            val response = authExpenseService.getIncome(income)
            return  if(response.isSuccessful){
                response.body()
            }else{
                null
            }
    }

    suspend fun getExpense(expense: String): Movements?{
        val response = authExpenseService.getExpense(expense)
        return if(response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

}