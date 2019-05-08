package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.repository.AccountRepository

class DashboardViewModel(
    private val repository: AccountRepository,
    val userId: Int
): ViewModel() {

    private val allAccounts: LiveData<List<Accounts>>

    init{
        allAccounts = repository.getAllAcoountsById(userId)
    }

    fun getAllAccounts() = allAccounts

    fun deleteAccount(account: Accounts) = repository.deleteAccountById(account)


}