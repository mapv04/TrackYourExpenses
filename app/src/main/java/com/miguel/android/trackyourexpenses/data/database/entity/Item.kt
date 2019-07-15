package com.miguel.android.trackyourexpenses.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:  Int?,
    val name: String,
    val quantity: Int,
    val price: Float
)