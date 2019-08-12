package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miguel.android.trackyourexpenses.data.database.entity.User

class RequestSignUp(user: User) {
    @SerializedName("name")
    @Expose
    private val name: String = user.name.toLowerCase().trim()
    @SerializedName("lastname")
    @Expose
    private val lastname: String = user.lastname.toLowerCase().trim()
    @SerializedName("email")
    @Expose
    private val email: String = user.email.trim()
    @SerializedName("username")
    @Expose
    private val username: String = user.username.toLowerCase().trim()
    @SerializedName("password")
    @Expose
    private val password: String = user.password.toLowerCase().trim()
}