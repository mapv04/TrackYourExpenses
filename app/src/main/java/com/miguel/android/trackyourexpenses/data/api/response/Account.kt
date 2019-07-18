package com.miguel.android.trackyourexpenses.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Account {
    @SerializedName("_id")
    @Expose
    val id: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("color")
    @Expose
    val color: Int? = null
    @SerializedName("imageLocation")
    @Expose
    val imageLocation: String? = null
    @SerializedName("lastUpdate")
    @Expose
    val lastUpdate: String? = null
    @SerializedName("user_id")
    @Expose
    val userId: String? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
}