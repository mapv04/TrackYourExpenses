package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository

class IncomesViewModel(
    repository: AccountActivityRepository,
    accountId: String
): ViewModel() {
    private val allIncomes: LiveData<List<Movements>>

    init{
        AccountActivityRepository.accountId = accountId
        allIncomes = repository.getAllIncomes()
    }

    fun getAllIncomes() = allIncomes

}