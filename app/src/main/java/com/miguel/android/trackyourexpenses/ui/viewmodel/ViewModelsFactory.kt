package com.miguel.android.trackyourexpenses.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository
import com.miguel.android.trackyourexpenses.data.repository.UserRepository

class LoginViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}

class RegisterViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

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

