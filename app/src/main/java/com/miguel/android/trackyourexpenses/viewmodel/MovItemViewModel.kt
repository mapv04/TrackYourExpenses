package com.miguel.android.trackyourexpenses.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import com.miguel.android.trackyourexpenses.repository.MovementRepository
import com.miguel.android.trackyourexpenses.ui.fragments.MovementDetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovItemViewModel(private val repository: MovementRepository): ViewModel() {
    var movement = MutableLiveData<Movements>()

    fun getIncomeData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                movement.postValue(repository.getIncome(MovementDetailsFragment.movementId))
            }catch (e: Exception){
                Log.e("MovItemViewModel", "Error: $e")
            }
        }
    }

    fun getExpenseData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                movement.postValue(repository.getExpense(MovementDetailsFragment.movementId))
            }catch (e: Exception){
                Log.e("MovItemViewModel", "Error: $e")
            }
        }
    }

}