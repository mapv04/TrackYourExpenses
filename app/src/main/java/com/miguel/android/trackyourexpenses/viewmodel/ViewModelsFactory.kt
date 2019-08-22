package com.miguel.android.trackyourexpenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.android.trackyourexpenses.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import com.miguel.android.trackyourexpenses.repository.MovementRepository
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

class DashboardViewModelFactory(
    private val repository: AccountRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository) as T
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

class MovementsViewModelFactory(
    private val repository: AccountActivityRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovementsViewModel(repository) as T
    }
}

class MovItemViewModelFactory(
    private val repository: MovementRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovItemViewModel(repository) as T
    }
}





