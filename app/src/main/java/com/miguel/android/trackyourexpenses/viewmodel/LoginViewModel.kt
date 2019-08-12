package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel() {

    private val repository = AuthRepository()
    val editTextUsername = MutableLiveData<String>()
    val editTextPassword = MutableLiveData<String>()
    var responseLogin = MutableLiveData<Int>()

    class UserLogin(val username: String, val password:String)
    private val _user = MutableLiveData<UserLogin>()

    val user: LiveData<UserLogin>
        get() = _user



    fun onLoginButtonClick(){
        if(editTextUsername.value != null && editTextPassword.value != null) {
            val user = UserLogin(editTextUsername.value!!, editTextPassword.value!!)
            _user.value = user
        }
    }



    fun doLogin(requestLogin: RequestLogin){
        viewModelScope.launch(Dispatchers.IO) {
            responseLogin.postValue(repository.login(requestLogin))
        }
    }


}