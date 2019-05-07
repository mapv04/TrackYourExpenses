package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.repository.AccountRepository

class NewAccountViewModel(
    private val repository: AccountRepository
): ViewModel() {

    val editTextAccountName = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()

    val accountName: LiveData<String>
        get() = _name

    fun addNewAccount(account: Accounts) = repository.addNewAccount(account)

    fun onButtonSaveClick(){
        if (editTextAccountName.value != null) {
            _name.value = editTextAccountName.value
        }
    }
}