package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.android.trackyourexpenses.repository.AccountRepository

class DashboardViewModelFactory(
    private val repository: AccountRepository,
    val userId: Int
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository, userId) as T
    }
}