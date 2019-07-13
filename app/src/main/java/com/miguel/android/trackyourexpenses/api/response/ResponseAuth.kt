package com.miguel.android.trackyourexpenses.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseAuth {
    @SerializedName("token")
    @Expose
    private val token: String? = null
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("lastname")
    @Expose
    private val lastname: String? = null
    @SerializedName("username")
    @Expose
    private val username: String? = null
}