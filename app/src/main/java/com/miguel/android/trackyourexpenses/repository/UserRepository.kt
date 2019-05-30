package com.miguel.android.trackyourexpenses.repository

import android.os.AsyncTask
import com.miguel.android.trackyourexpenses.database.dao.UserDao
import com.miguel.android.trackyourexpenses.database.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(private val userDao: UserDao) {


    fun authentication(username:String, password:String): User? = Authentication(userDao).execute(username, password).get()

    fun checkIfExists(username: String) = CheckAsyncTask(userDao).execute(username)


    suspend fun addNewUser(user: User) = withContext(Dispatchers.IO) {
        userDao.insertNewUser(user)
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
        ////ASYNCTASK
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




