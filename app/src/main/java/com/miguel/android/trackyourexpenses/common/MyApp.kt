package com.miguel.android.trackyourexpenses.common

import android.app.Application
import com.miguel.android.trackyourexpenses.dagger.AppComponent
import com.miguel.android.trackyourexpenses.dagger.DaggerAppComponent
import com.miguel.android.trackyourexpenses.dagger.NetworkModule
import com.miguel.android.trackyourexpenses.dagger.RepositoriesModule

class MyApp: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent.builder()
            .repositoriesModule(RepositoriesModule())
            .networkModule(NetworkModule())
            .build()

    }

    fun getComponent() = component

    companion object {
        lateinit var instance: MyApp
            private set

    }
}