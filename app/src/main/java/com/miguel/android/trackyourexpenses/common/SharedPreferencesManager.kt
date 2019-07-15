package com.miguel.android.trackyourexpenses.common

import android.content.Context


class SharedPreferencesManager {

    companion object {
        private const val APP_SETTINGS_FILE = "APP_SETTINGS"

        fun setSomeStringValue(dataLabel: String, dataValue: String, context: Context) {
            val editor = context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).edit()
            editor.putString(dataLabel, dataValue)
            editor.apply()
        }

        fun getSomeStringValue(dataLabel: String, context: Context) =
            context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).getString(dataLabel, null)
    }

}