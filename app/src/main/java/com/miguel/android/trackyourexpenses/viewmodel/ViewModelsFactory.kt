package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.android.trackyourexpenses.repository.AccountRepository

class LoginViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}


class NewAccountViewModelFactory(
    private val repository: AccountRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewAccountViewModel(repository) as T
    }
}


class DashboardViewModelFactory(
    private val repository: AccountRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository) as T
    }
}




