package com.miguel.android.trackyourexpenses.dagger

import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import com.miguel.android.trackyourexpenses.ui.fragments.LoginFragment
import com.miguel.android.trackyourexpenses.ui.fragments.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class,NetworkModule::class])
interface AppComponent {

    /*fun getApiInterface(): ExpensesService
    fun getAuthApiInterface(): AuthExpensesService*/
    fun getAuthRepository(fragment: LoginFragment)
    
    fun getAuthRepository(fragment: RegisterFragment)
}