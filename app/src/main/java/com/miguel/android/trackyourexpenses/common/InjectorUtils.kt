package com.miguel.android.trackyourexpenses.common

import android.content.Context
import com.miguel.android.trackyourexpenses.data.database.MoneyManagerRoomDB
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.viewmodel.*

object InjectorUtils{



    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository.getInstance(
            MoneyManagerRoomDB.getDatabase(context.applicationContext).accountDao()
        )
    }



    fun provideLoginViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory()
    }


    fun provideNewAccountViewModelFactory(context: Context): NewAccountViewModelFactory{
        val repository = getAccountRepository(context)
        return NewAccountViewModelFactory(repository)
    }

    fun provideDashboardViewModelFactory(context: Context): DashboardViewModelFactory{
        val repository = getAccountRepository(context)
        return DashboardViewModelFactory(repository)
    }

    fun provideMovIncomeViewModelFactory(): MovIncomeViewModelFactory{
        return MovIncomeViewModelFactory()
    }

    fun provideMovExpenseViewModelFactory(): MovExpenseViewModelFactory{
        return MovExpenseViewModelFactory()
    }

}