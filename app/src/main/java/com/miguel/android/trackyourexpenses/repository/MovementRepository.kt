package com.miguel.android.trackyourexpenses.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovementRepository {

    private var movement : MutableLiveData<Movements>? = null
    private val authExpenseService: AuthExpensesService
    private val authExpensesClient: AuthExpensesClient = AuthExpensesClient.instance

    init {
        authExpenseService = authExpensesClient.expensesService
    }

    fun getIncome(income: String): MutableLiveData<Movements>?{
        movement = MutableLiveData()
        val call: Call<Movements> = authExpenseService.getIncome(income)
        call.enqueue(object: Callback<Movements>{
            override fun onResponse(call: Call<Movements>, response: Response<Movements>) {
                if(response.isSuccessful){
                    movement?.value = response.body()
                }
                else{
                    Log.i(TAG, "Response not successful ")
                }
            }

            override fun onFailure(call: Call<Movements>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
        return movement
    }

    fun getExpense(expense: String): MutableLiveData<Movements>?{
        movement = MutableLiveData()
        val call: Call<Movements> = authExpenseService.getExpense(expense)
        call.enqueue(object: Callback<Movements> {
            override fun onResponse(call: Call<Movements>, response: Response<Movements>) {
                if(response.isSuccessful){
                    movement?.value = response.body()
                }
                else{
                    Log.i(TAG, "Response not succesful ")
                }
            }

            override fun onFailure(call: Call<Movements>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
        return movement

    }

    companion object{
        const val TAG = "MovementRepository"
    }
}