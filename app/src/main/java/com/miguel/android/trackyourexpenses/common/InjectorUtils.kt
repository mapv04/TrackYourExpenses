package com.miguel.android.trackyourexpenses.common

import com.miguel.android.trackyourexpenses.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import com.miguel.android.trackyourexpenses.repository.MovementRepository
import com.miguel.android.trackyourexpenses.viewmodel.*

object InjectorUtils{

    fun provideLoginViewModelFactory(repository: AuthRepository) = LoginViewModelFactory(repository)

    fun provideRegisterViewModelFactory(repository: AuthRepository) = RegisterViewModelFactory(repository)

    fun provideNewAccountViewModelFactory(repository: AccountRepository) = NewAccountViewModelFactory(repository)

    fun provideDashboardViewModelFactory(repository: AccountRepository) = DashboardViewModelFactory(repository)

    fun provideMovementsViewModelFactory(repository: AccountActivityRepository) = MovementsViewModelFactory(repository)

    fun provideMovItemViewModelFactory(repository: MovementRepository) = MovItemViewModelFactory(repository)

}