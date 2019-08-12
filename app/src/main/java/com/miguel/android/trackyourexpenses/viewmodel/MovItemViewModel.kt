package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.data.repository.MovementRepository
import com.miguel.android.trackyourexpenses.ui.fragments.MovementDetailsFragment

class MovItemViewModel(movementType: String): ViewModel() {
    var movement: LiveData<Movements>?
    private val repository = MovementRepository()


    init{
        movement = if(movementType == "income"){
            getIncomeData()
        } else{
            getExpenseData()
        }
    }

    private fun getIncomeData(): LiveData<Movements>?{
        movement = repository.getIncome(MovementDetailsFragment.movementId)
        return movement
    }

    private fun getExpenseData(): LiveData<Movements>?{
        movement = repository.getExpense(MovementDetailsFragment.movementId)

        return movement
    }

}