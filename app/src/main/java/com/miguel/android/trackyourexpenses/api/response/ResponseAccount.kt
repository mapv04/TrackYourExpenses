package com.miguel.android.trackyourexpenses.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseAccount {
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("color")
    @Expose
    private val color: String? = null
    @SerializedName("imageEncoded")
    @Expose
    private val imageEncoded: String? = null
    @SerializedName("lastUpdate")
    @Expose
    private val lastUpdate: String? = null
}