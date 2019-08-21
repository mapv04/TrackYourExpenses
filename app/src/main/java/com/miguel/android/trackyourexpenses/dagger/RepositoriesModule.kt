package com.miguel.android.trackyourexpenses.dagger

import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideAuthRepository(expensesService: ExpensesService) = AuthRepository(expensesService)
}