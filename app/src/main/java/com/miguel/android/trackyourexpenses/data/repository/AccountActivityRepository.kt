package com.miguel.android.trackyourexpenses.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.api.request.RequestMov
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.database.dao.AccountActivityDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivityRepository{

    private var allIncomes: MutableLiveData<List<Movements>>? = MutableLiveData()
    private var allExpenses: MutableLiveData<List<Movements>>? = MutableLiveData()
    private val authExpenseService: AuthExpensesService
    private val authExpensesClient: AuthExpensesClient = AuthExpensesClient.instance

    init{
        authExpenseService = authExpensesClient.expensesService
        //allIncomes = getAllIncomes()
    }

    fun getAllIncomes(): MutableLiveData<List<Movements>>{
        val call: Call<List<Movements>> = authExpenseService.getAllIncomes(accountId)
        call.enqueue(object: Callback<List<Movements>>{
            override fun onResponse(call: Call<List<Movements>>, response: Response<List<Movements>>) {
                if (response.isSuccessful){
                    allIncomes?.value = response.body()
                }
                else{
                    Log.i(TAG, "Something went wrong")
                }
            }

            override fun onFailure(call: Call<List<Movements>>, t: Throwable) {
                Log.e(TAG, "Connection error: $t")
            }
        })
        return allIncomes!!
    }

    fun getAllExpenses(): MutableLiveData<List<Movements>>{
        val call: Call<List<Movements>> = authExpenseService.getAllExpenses(accountId)
        call.enqueue(object: Callback<List<Movements>>{
            override fun onResponse(call: Call<List<Movements>>, response: Response<List<Movements>>) {
                if(response.isSuccessful){
                    allExpenses?.value = response.body()
                }
                else{
                    Log.i(TAG, "Something went wrong")
                }
            }

            override fun onFailure(call: Call<List<Movements>>, t: Throwable) {
                Log.e(TAG, "Connection error: $t")
            }
        })

        return allExpenses!!
    }

    fun createNewIncome(movs: Movs): Movements {

    }

    fun createNewExpense(movs: Movs): Movements {

    }

    companion object{
        private const val TAG = "AccountActivityRepo"
       lateinit var accountId: String
        /**
         * TODO("Delete code below")
         */
        @Volatile
        private var instance: AccountActivityRepository? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: AccountActivityRepository().also{ instance = it}
            }

    }
}