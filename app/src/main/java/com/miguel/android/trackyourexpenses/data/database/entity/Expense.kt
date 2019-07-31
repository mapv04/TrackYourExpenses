package com.miguel.android.trackyourexpenses.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "incomes" ,foreignKeys = arrayOf(
    ForeignKey(
        entity = Accounts::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("account_id")
    )
))
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: String? = null,
    val name: String,
    val description: String,
    val date: String,
    val total: Float,
    @ColumnInfo(name = "account_id") val accountId: String
)