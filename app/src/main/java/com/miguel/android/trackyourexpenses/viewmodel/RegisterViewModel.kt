package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.database.entity.User
import com.miguel.android.trackyourexpenses.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel(
    private val repository: UserRepository
): ViewModel() {

    private val _user = MutableLiveData<User>()
    val editTextName = MutableLiveData<String>()
    val editTextUsername = MutableLiveData<String>()
    val editTextEmail = MutableLiveData<String>()
    val editTextPassword = MutableLiveData<String>()

    // The data exposed to the fragment
    val user: LiveData<User>
        get() = _user


    fun onSignUpButtonClick(){
        if(editTextName.value != null && editTextUsername.value != null &&
            editTextEmail.value != null && editTextPassword.value != null) {

            val newUser = User(
                null, editTextName.value!!,
                editTextUsername.value!!,
                editTextEmail.value!!,
                editTextPassword.value!!
            )
            _user.value = newUser
        }
    }



    fun userExists(username: String): Int = repository.checkIfExists(username).get()


    fun addNewUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
            repository.addNewUser(user)
        }

    fun onUserCreated(){
        _user.value = null
    }

    override fun onCleared() {
        super.onCleared()
    }

}