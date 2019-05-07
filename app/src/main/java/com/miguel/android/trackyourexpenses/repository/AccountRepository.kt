package com.miguel.android.trackyourexpenses.repository

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.miguel.android.trackyourexpenses.database.dao.AccountsDao
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AccountRepository(private val accountDao: AccountsDao) {

    @SuppressLint("CheckResult")
    fun addNewAccount(account: Accounts){
        Observable.just(account)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({account ->
                accountDao.addNewAccount(account)
            }, {error -> Log.e(TAG, "Error creating the new account", error)})
    }

    fun getAllAcoountsById(id: Int) : LiveData<List<Accounts>> = GetAccountsAsyncTask(accountDao).execute(id).get()



    companion object{
        private const val TAG = "AccountRepository"

        @Volatile
        private var instance: AccountRepository? = null

        fun getInstance(accountDao: AccountsDao) =
            instance ?: synchronized(this){
                instance ?: AccountRepository(accountDao).also{ instance = it}
            }
    }

    class GetAccountsAsyncTask(dao: AccountsDao): AsyncTask<Int?, Void, LiveData<List<Accounts>>>(){
        private val accountDao = dao

        override fun doInBackground(vararg userId: Int?): LiveData<List<Accounts>> {
            return accountDao.getAllAccounts(userId[0]!!)
        }
    }



}