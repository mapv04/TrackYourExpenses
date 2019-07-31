package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.ui.RecyclerViewIncomes

class IncomesViewModel: ViewModel() {
    private val repository = AccountActivityRepository()

    private var allIncomes: LiveData<List<Movements>>

    init{
        allIncomes = repository.getAllIncomes()
    }

    fun getAllIncomes():LiveData<List<Movements>>{
        allIncomes = repository.getAllIncomes()
        return allIncomes
    }



}