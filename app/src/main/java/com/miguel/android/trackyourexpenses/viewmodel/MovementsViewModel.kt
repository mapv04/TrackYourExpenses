package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.repository.AccountActivityRepository

class MovementsViewModel: ViewModel() {
    private val repository = AccountActivityRepository()

    private var allIncomes: LiveData<List<Movements>>
    private var allExpenses: LiveData<List<Movements>>

    init{
        allIncomes = repository.getAllIncomes()
        allExpenses = repository.getAllExpenses()
    }

    fun getAllIncomes():LiveData<List<Movements>>{
        allIncomes = repository.getAllIncomes()
        return allIncomes
    }

    fun getAllExpenses(): LiveData<List<Movements>> {
        allExpenses = repository.getAllExpenses()
        return allExpenses
    }

}