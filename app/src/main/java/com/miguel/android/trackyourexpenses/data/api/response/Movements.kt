package com.miguel.android.trackyourexpenses.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miguel.android.trackyourexpenses.data.ItemMov

class Movements {
    @SerializedName("_id")
    @Expose
    val id: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("date")
    @Expose
    val date: String? = null
    @SerializedName("total")
    @Expose
    val total: Double? = null
    @SerializedName("account_id")
    @Expose
    val accountId: String? = null
    @SerializedName("movs")
    @Expose
    val movs: List<ItemMov>? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
}