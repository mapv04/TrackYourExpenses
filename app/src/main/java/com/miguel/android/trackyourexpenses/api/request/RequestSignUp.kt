package com.miguel.android.trackyourexpenses.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestSignUp {
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("lastname")
    @Expose
    private val lastname: String? = null
    @SerializedName("email")
    @Expose
    private val email: String? = null
    @SerializedName("username")
    @Expose
    private val username: String? = null
    @SerializedName("password")
    @Expose
    private val password: String? = null
}