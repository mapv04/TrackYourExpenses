package com.miguel.android.trackyourexpenses.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "accounts", foreignKeys = arrayOf(ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("user_id")
)))
data class Accounts(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val color: Int,
    val imageEncoded: String,
    val lastUpdate: String,
    @ColumnInfo(name = "user_id") val userId: Int
)