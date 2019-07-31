package com.miguel.android.trackyourexpenses.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movs(val name: String,
           val description: String,
           val date: String,
           val total: Double,
           val items: List<ItemMov>
)

class ItemMov(nameMov: String, itemPP: String, totalMov: Double){
    @SerializedName("mov")
    @Expose
    val name = nameMov
    @SerializedName("whereOrWho")
    @Expose
    val item = itemPP
    @SerializedName("quantity")
    @Expose
    val total = totalMov
}