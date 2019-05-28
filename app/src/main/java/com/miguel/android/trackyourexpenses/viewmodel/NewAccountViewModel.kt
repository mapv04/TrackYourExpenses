package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewAccountViewModel(
    private val repository: AccountRepository
): ViewModel() {

    val editTextAccountName = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()

    val accountName: LiveData<String>
        get() = _name

    fun addNewAccount(account: Accounts) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewAccount(account)
    }

    fun onButtonSaveClick(){
        if (editTextAccountName.value != null) {
            _name.value = editTextAccountName.value
        }
    }
}