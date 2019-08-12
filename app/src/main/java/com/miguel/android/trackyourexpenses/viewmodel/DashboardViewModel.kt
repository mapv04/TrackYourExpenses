package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository

class DashboardViewModel(
    private val repository: AccountRepository
): ViewModel() {

    private var allAccounts: LiveData<List<Account>>

    init{
        allAccounts = repository.getAllAccounts()
    }

    fun getAllAccounts():LiveData<List<Account>> {
        allAccounts = repository.getAllAccounts()
        return allAccounts
    }

    fun deleteAccount(account: Accounts) = repository.deleteAccount(account)



}