package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel (private val repository: AuthRepository) : ViewModel() {

    val responseRegister = MutableLiveData<Int>()
    //private val repository = AuthRepository()
    val editTextName = MutableLiveData<String>()
    val editTextLName = MutableLiveData<String>()
    val editTextUsername = MutableLiveData<String>()
    val editTextEmail = MutableLiveData<String>()
    val editTextPassword = MutableLiveData<String>()


    fun register(requestRegister: RequestSignUp){
        viewModelScope.launch(Dispatchers.IO) {
            responseRegister.postValue(repository.register(requestRegister))
        }
    }

}