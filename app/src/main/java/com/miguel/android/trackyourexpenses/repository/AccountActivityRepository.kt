package com.miguel.android.trackyourexpenses.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.api.request.RequestMov
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.ui.fragments.AccountDetailsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivityRepository{

    private var allIncomes: MutableLiveData<List<Movements>>?
    private var allExpenses: MutableLiveData<List<Movements>>?
    private val authExpenseService: AuthExpensesService
    private val authExpensesClient: AuthExpensesClient = AuthExpensesClient.instance

    init{
        authExpenseService = authExpensesClient.expensesService
        allIncomes = getAllIncomes()
        allExpenses = getAllExpenses()
    }

    fun getAllIncomes(): MutableLiveData<List<Movements>>{
        allIncomes = allIncomes ?: MutableLiveData()

        Log.i(TAG, "GET incomes from accountId: ${AccountDetailsFragment.account}")

        val call: Call<List<Movements>> = authExpenseService.getAllIncomes(AccountDetailsFragment.account)
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
        allExpenses = allExpenses ?: MutableLiveData()

        val call: Call<List<Movements>> = authExpenseService.getAllExpenses(AccountDetailsFragment.account)
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

    fun createNewIncome(movs: Movs){
        val requestIncome = RequestMov(AccountDetailsFragment.account, movs)
        val call: Call<Movements> = authExpenseService.createNewIncome(requestIncome)
        call.enqueue(object: Callback<Movements>{
            override fun onResponse(call: Call<Movements>, response: Response<Movements>) {
               if(response.isSuccessful){

                   val list = mutableListOf(response.body()!!)
                   allIncomes?.value?.forEach {
                       list.add(it)
                   }

                   allIncomes?.value = list


               }
                else{
                    Log.i(TAG, "Response not successful ")
               }
            }

            override fun onFailure(call: Call<Movements>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
    }

    fun createNewExpense(movs: Movs){
        val requestIncome = RequestMov(AccountDetailsFragment.account, movs)
        val call: Call<Movements> = authExpenseService.createNewExpense(requestIncome)
        call.enqueue(object: Callback<Movements>{
            override fun onResponse(call: Call<Movements>, response: Response<Movements>) {
                if(response.isSuccessful){
                    val list = mutableListOf(response.body()!!)
                    allExpenses?.value?.forEach {
                        list.add(it)
                    }

                    allExpenses?.value = list
                }
                else{
                    Log.i(TAG, "Response not succesful ")
                }
            }

            override fun onFailure(call: Call<Movements>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
    }



    companion object{
        private const val TAG = "AccountActivityRepo"

    }
}