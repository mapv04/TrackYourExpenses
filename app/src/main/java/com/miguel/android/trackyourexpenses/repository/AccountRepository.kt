package com.miguel.android.trackyourexpenses.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.miguel.android.trackyourexpenses.data.api.request.RequestAccount
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.api.response.AccountDeleted
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.database.dao.AccountsDao
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(private val authExpenseService: AuthExpensesService) {

    private var allAccounts: MutableLiveData<List<Account>>?


    init{
        //authExpenseService = authExpensesClient.expensesService
        allAccounts = getAllAccounts()
    }


    fun getAllAccounts(): MutableLiveData<List<Account>>{
        allAccounts = allAccounts ?: MutableLiveData()

        val call: Call<List<Account>> = authExpenseService.getAllAccounts()
        call.enqueue(object: Callback<List<Account>>{
            override fun onResponse(call: Call<List<Account>>, response: Response<List<Account>>) {
                if(response.isSuccessful){
                    allAccounts?.value = response.body()
                }
                else{
                    Log.i(TAG, "Something went wrong")
                }
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
        return allAccounts!!
    }

    fun deleteAccount(account: Accounts){
        val call = authExpenseService.deleteAccount(account.id)
        call.enqueue(object: Callback<AccountDeleted>{
            override fun onResponse(call: Call<AccountDeleted>, response: Response<AccountDeleted>) {
                if(response.isSuccessful){
                    Log.i(TAG, "${response.body()?.message} ID: ${response.body()?.id} UserID: ${response.body()?.userId}")
                    val list = mutableListOf<Account>()
                    allAccounts?.value?.forEach{
                        if (it.id != response.body()?.id) {
                            list.add(it)
                        }
                    }
                    allAccounts?.value = list
                }
                else{
                    Log.d(TAG, "Failure request")
                }
            }

            override fun onFailure(call: Call<AccountDeleted>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
    }

    fun createNewAccount(account: Accounts) {
        val requestAccount = RequestAccount(account)
        val call = authExpenseService.createNewAccount(requestAccount)
        call.enqueue(object: Callback<Account>{
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if (response.isSuccessful){
                    val list = mutableListOf(response.body()!!)
                    allAccounts?.value?.forEach{
                        list.add(it)
                    }

                    allAccounts?.value = list
                }
                else{
                    Log.d(TAG, "Failure request")
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e(TAG, "Network failure: $t")
            }
        })
    }

    companion object{
        private const val TAG = "AccountReposotory"

    }

}