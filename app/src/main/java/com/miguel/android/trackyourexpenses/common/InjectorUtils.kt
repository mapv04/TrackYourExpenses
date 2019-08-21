package com.miguel.android.trackyourexpenses.common

import android.content.Context
import com.miguel.android.trackyourexpenses.data.database.MoneyManagerRoomDB
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import com.miguel.android.trackyourexpenses.viewmodel.*

object InjectorUtils{



    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository.getInstance(
            MoneyManagerRoomDB.getDatabase(context.applicationContext).accountDao()
        )
    }


    fun provideLoginViewModelFactory(repository: AuthRepository): LoginViewModelFactory {
        return LoginViewModelFactory(repository)
    }

    fun provideRegisterViewModelFactory(repository: AuthRepository): RegisterViewModelFactory {
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



}