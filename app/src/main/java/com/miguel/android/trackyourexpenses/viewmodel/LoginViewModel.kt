package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel (private val repository: AuthRepository): ViewModel() {

    //private val repository: AuthRepository
    val editTextUsername = MutableLiveData<String>()
    val editTextPassword = MutableLiveData<String>()

    var responseLogin = MutableLiveData<Int>()


    fun doLogin(requestLogin: RequestLogin){
        viewModelScope.launch(Dispatchers.IO) {
            responseLogin.postValue(repository.login(requestLogin))
        }
    }


}