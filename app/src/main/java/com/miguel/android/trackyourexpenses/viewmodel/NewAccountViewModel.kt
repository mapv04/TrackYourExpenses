package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewAccountViewModel(
    private val repository: AccountRepository
): ViewModel() {


    fun addNewAccount(account: Accounts) = repository.createNewAccount(account)


}