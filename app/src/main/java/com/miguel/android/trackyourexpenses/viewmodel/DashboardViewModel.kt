package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: AccountRepository
): ViewModel() {

    private val allAccounts: LiveData<List<Account>>

    init{
        allAccounts = repository.getAllAccounts()
    }

    fun getAllAccounts() = allAccounts

    fun deleteAccount(account: Accounts) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAccount(account)
    }


}