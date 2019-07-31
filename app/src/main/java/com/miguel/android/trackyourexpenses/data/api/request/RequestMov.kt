package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miguel.android.trackyourexpenses.data.Movs

class RequestMov(
    accountId: String,
    movement: Movs) {

    @SerializedName("accountId")
    @Expose
    private val accountId: String = accountId

    @SerializedName("name")
    @Expose
    val name = movement.name

    @SerializedName("description")
    @Expose
    val description = movement.description

    @SerializedName("date")
    @Expose
    val date = movement.date

    @SerializedName("total")
    @Expose
    val total = movement.total

    @SerializedName("movs")
    @Expose
    val list = movement.items


}