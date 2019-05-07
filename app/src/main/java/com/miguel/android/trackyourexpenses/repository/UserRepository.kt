package com.miguel.android.trackyourexpenses.repository

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.miguel.android.trackyourexpenses.database.dao.UserDao
import com.miguel.android.trackyourexpenses.database.entity.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class UserRepository(private val userDao: UserDao) {


    fun authentication(username:String, password:String): User? = Authentication(userDao).execute(username, password).get()

    fun checkIfExists(username: String) = CheckAsyncTask(userDao).execute(username)

    @SuppressLint("CheckResult")
    fun addNewUser(user: User){
        Observable.just(user)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({user ->
                userDao.insertNewUser(user)
            }, {error -> Log.e(TAG, "Error adding the new user: ", error)})
    }





    companion object{
        private const val  TAG = "UserRepository"
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
                instance ?: synchronized(this){
                    instance ?: UserRepository(userDao).also{ instance = it}
                }

    }


    /*
    ////ASYCNTASK
     */

    class CheckAsyncTask(dao: UserDao): AsyncTask<String?, Void, Int>(){
        private val userDao = dao

        override fun doInBackground(vararg param: String?): Int {
            for(username in param){
                return userDao.checkIfExists(username)
            }
            return 0
        }
    }

    class Authentication(dao: UserDao): AsyncTask<String, Void, User?>(){
        private val userDao = dao

        override fun doInBackground(vararg user: String): User? {
            return userDao.authentication(user[0], user[1])
        }

    }

}


