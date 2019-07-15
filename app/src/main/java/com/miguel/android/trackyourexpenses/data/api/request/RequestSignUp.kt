package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miguel.android.trackyourexpenses.data.database.entity.User

class RequestSignUp(user: User) {
    @SerializedName("name")
    @Expose
    private val name: String = user.name
    @SerializedName("lastname")
    @Expose
    private val lastname: String = user.lastname
    @SerializedName("email")
    @Expose
    private val email: String = user.email
    @SerializedName("username")
    @Expose
    private val username: String = user.username
    @SerializedName("password")
    @Expose
    private val password: String = user.password
}