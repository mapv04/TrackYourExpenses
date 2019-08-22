package com.miguel.android.trackyourexpenses.dagger

import com.miguel.android.trackyourexpenses.ui.fragments.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class,NetworkModule::class])
interface AppComponent {

    fun getAuthRepository(fragment: LoginFragment)
    
    fun getAuthRepository(fragment: RegisterFragment)

    fun getAccountRepository(fragment: DashboardFragment)

    fun getAccountRepository(fragment: NewAccountFragment)

    fun getMovementRepository(fragment: MovementDetailsFragment)

    fun getAccountActivityRepository(fragment: NewMovementFragment)

    fun getAccountActivityRepository(fragment: RecyclerViewIncomes)

    fun getAccountActivityRepository(fragment: RecyclerViewExpenses)

}