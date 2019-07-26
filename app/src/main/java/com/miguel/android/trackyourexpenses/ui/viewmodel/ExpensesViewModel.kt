package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository

class ExpensesViewModel(
    repository: AccountActivityRepository,
    accountId: String
): ViewModel() {

    private val allExpenses: LiveData<List<Movements>>

    init{
        AccountActivityRepository.accountId = accountId
        allExpenses = repository.getAllExpenses()
    }

    fun getAllExpenses() = allExpenses
}