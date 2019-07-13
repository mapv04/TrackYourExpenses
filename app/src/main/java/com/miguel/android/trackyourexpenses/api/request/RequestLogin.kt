package com.miguel.android.trackyourexpenses.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestLogin{
    @SerializedName("username")
    @Expose
    private val username: String? = null
    @SerializedName("password")
    @Expose
    private val password: String? = null
}