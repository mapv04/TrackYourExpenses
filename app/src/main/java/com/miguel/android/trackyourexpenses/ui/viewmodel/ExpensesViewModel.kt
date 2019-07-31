package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository

class ExpensesViewModel: ViewModel() {

    private val repository = AccountActivityRepository()

    private var allExpenses: LiveData<List<Movements>>

    init{
        allExpenses = repository.getAllExpenses()
    }

    fun getAllExpenses(): LiveData<List<Movements>> {
        allExpenses = repository.getAllExpenses()
        return allExpenses
    }
}