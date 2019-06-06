package com.miguel.android.trackyourexpenses.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "incomes" ,foreignKeys = arrayOf(ForeignKey(
    entity = Accounts::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("account_id")
)))
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val store: String,
    val total: Float,
    @ColumnInfo(name = "account_id") val accountId: Int
)