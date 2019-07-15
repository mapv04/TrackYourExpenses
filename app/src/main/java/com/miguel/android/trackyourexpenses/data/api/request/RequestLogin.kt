package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestLogin(username: String, password: String){
    @SerializedName("username")
    @Expose
    private val username: String = username
    @SerializedName("password")
    @Expose
    private val password: String = password
}