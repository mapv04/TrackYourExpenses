package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository

class NewAccountViewModel(
    private val repository: AccountRepository
): ViewModel() {


    fun addNewAccount(account: Accounts) = repository.createNewAccount(account)


}