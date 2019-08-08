package com.miguel.android.trackyourexpenses.common

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesManager {

    companion object {
        private const val APP_SETTINGS_FILE = "APP_SETTINGS"

        private fun getSharedPreferences(): SharedPreferences {
            return MyApp.instance.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun setSomeStringValue(dataLabel: String, dataValue: String) {
            val editor = getSharedPreferences().edit()
            editor.putString(dataLabel, dataValue)
            editor.apply()
        }

        fun getSomeStringValue(dataLabel: String) =
           getSharedPreferences().getString(dataLabel, null)

        fun setSomeBoolValue(dataLabel: String, dataValue: Boolean){
            val editor = getSharedPreferences().edit()
            editor.putBoolean(dataLabel, dataValue)
            editor.apply()
        }

        fun getSomeBoolValue(dataLabel: String) =
            getSharedPreferences().getBoolean(dataLabel, false)
    }

}