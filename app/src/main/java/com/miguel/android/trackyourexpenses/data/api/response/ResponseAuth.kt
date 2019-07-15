package com.miguel.android.trackyourexpenses.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseAuth {
    @SerializedName("token")
    @Expose
    val token: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("lastname")
    @Expose
    val lastname: String? = null
    @SerializedName("username")
    @Expose
    val username: String? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
}