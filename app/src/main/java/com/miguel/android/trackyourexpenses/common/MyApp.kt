package com.miguel.android.trackyourexpenses.common

import android.app.Application

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set

    }
}