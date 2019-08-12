package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.repository.AccountRepository

class NewAccountViewModel(
    private val repository: AccountRepository
): ViewModel() {


    fun addNewAccount(account: Accounts) = repository.createNewAccount(account)


}