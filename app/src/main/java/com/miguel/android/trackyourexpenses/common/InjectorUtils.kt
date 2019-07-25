package com.miguel.android.trackyourexpenses.common

import android.content.Context
import com.miguel.android.trackyourexpenses.data.database.MoneyManagerRoomDB
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.data.repository.AccountRepository
import com.miguel.android.trackyourexpenses.data.repository.UserRepository
import com.miguel.android.trackyourexpenses.ui.viewmodel.*

object InjectorUtils{

    private fun getUserRepository(context: Context): UserRepository{
        return UserRepository.getInstance(
            MoneyManagerRoomDB.getDatabase(context.applicationContext).userDao()
        )
    }

    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository.getInstance(
            MoneyManagerRoomDB.getDatabase(context.applicationContext).accountDao()
        )
    }

    private fun getAccountActivityRepository(): AccountActivityRepository{
        return AccountActivityRepository.getInstance()
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val repository = getUserRepository(context)
        return LoginViewModelFactory(repository)
    }

    fun provideRegisterViewModelFactory(context: Context): RegisterViewModelFactory {
        val repository = getUserRepository(context)
        return RegisterViewModelFactory(repository)
    }

    fun provideNewAccountViewModelFactory(context: Context): NewAccountViewModelFactory{
        val repository = getAccountRepository(context)
        return NewAccountViewModelFactory(repository)
    }

    fun provideDashboardViewModelFactory(context: Context): DashboardViewModelFactory{
        val repository = getAccountRepository(context)
        return DashboardViewModelFactory(repository)
    }

    fun provideIncomesViewModelFactory(accountId: String): IncomesViewModelFactory {
        val repository = getAccountActivityRepository()
        return IncomesViewModelFactory(repository, accountId)
    }
}