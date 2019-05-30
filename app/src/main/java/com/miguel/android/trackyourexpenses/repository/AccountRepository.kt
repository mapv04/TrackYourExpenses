package com.miguel.android.trackyourexpenses.repository

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.miguel.android.trackyourexpenses.database.dao.AccountsDao
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(private val accountDao: AccountsDao) {

    suspend fun addNewAccount(account: Accounts) =
        withContext(Dispatchers.IO) {
            accountDao.addNewAccount(account)
        }


    fun getAllAcoountsById(id: Int) : LiveData<List<Accounts>> = GetAccountsAsyncTask(accountDao).execute(id).get()

    @SuppressLint("CheckResult")
    fun deleteAccountById(account: Accounts) {
        Observable.just(account)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ account ->
                accountDao.deleteAccount(account)
            }, { error -> Log.e(TAG, "Error deleting the account: ", error) })
    }




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