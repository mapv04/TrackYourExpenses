package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.database.entity.User
import com.miguel.android.trackyourexpenses.data.repository.UserRepository


class LoginViewModel(
    private val repository: UserRepository
): ViewModel() {

    val editTextUsername = MutableLiveData<String>()
    val editTextPassword = MutableLiveData<String>()

    class UserLogin(val username: String, val password:String)
    private val _user = MutableLiveData<UserLogin>()

    val user: LiveData<UserLogin>
        get() = _user


    fun authentication(username: String, password:String): User? = repository.authentication(username, password)

    fun onLoginButtonClick(){
        if(editTextUsername.value != null && editTextPassword.value != null) {
            val user = UserLogin(editTextUsername.value!!, editTextPassword.value!!)
            _user.value = user
        }
    }



}