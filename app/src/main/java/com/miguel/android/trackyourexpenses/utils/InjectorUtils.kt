package com.miguel.android.trackyourexpenses.utils

import android.content.Context
import com.miguel.android.trackyourexpenses.viewmodel.LoginViewModelFactory
import com.miguel.android.trackyourexpenses.viewmodel.RegisterViewModelFactory
import com.miguel.android.trackyourexpenses.database.MoneyManagerRoomDB
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.UserRepository
import com.miguel.android.trackyourexpenses.viewmodel.DashboardViewModelFactory
import com.miguel.android.trackyourexpenses.viewmodel.NewAccountViewModelFactory

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

    fun provideDashboardViewModelFactory(context: Context, userId: Int): DashboardViewModelFactory{
        val repository = getAccountRepository(context)
        return DashboardViewModelFactory(repository, userId)
    }
}