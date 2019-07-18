package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts

class RequestAccount(account: Accounts) {
    @SerializedName("_id")
    @Expose
    private val id: String? = account.id
    @SerializedName("name")
    @Expose
    private val name: String = account.name
    @SerializedName("color")
    @Expose
    private val color: Int? = account.color
    @SerializedName("imageLocation")
    @Expose
    private val imageLocation: String? = account.imageLocation
    @SerializedName("lastUpdate")
    @Expose
    private val lastUpdate: String = account.lastUpdate

}