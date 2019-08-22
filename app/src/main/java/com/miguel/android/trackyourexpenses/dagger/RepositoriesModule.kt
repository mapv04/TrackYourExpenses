package com.miguel.android.trackyourexpenses.dagger

import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import com.miguel.android.trackyourexpenses.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.repository.AccountRepository
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import com.miguel.android.trackyourexpenses.repository.MovementRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideAuthRepository(expensesService: ExpensesService) = AuthRepository(expensesService)

    @Provides
    @Singleton
    fun provideMovementRepository(authExpensesService: AuthExpensesService) = MovementRepository(authExpensesService)

    @Provides
    @Singleton
    fun provideAccountRepository(authExpensesService: AuthExpensesService) = AccountRepository(authExpensesService)

    @Provides
    @Singleton
    fun providesAccountActivityRepository(authExpensesService: AuthExpensesService) = AccountActivityRepository(authExpensesService)
}