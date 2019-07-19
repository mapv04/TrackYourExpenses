package com.miguel.android.trackyourexpenses.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccountDeleted {
    @SerializedName("_id")
    @Expose
    val id: String? = null
    @SerializedName("user_id")
    @Expose
    val userId: String? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
}