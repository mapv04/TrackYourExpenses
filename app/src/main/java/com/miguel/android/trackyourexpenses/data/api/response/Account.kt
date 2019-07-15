package com.miguel.android.trackyourexpenses.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Account {
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("color")
    @Expose
    val color: Int? = null
    @SerializedName("imageEncoded")
    @Expose
    val imageEncoded: String? = null
    @SerializedName("lastUpdate")
    @Expose
    val lastUpdate: String? = null
}