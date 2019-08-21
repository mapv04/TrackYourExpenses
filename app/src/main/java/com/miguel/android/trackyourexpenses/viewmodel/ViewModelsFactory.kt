package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Provider


class LoginViewModelFactory(
    private val repository: AuthRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}

class RegisterViewModelFactory(
    private val repository: AuthRepository
): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
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




