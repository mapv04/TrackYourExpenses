package com.miguel.android.trackyourexpenses.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "incomes_items", foreignKeys = arrayOf(ForeignKey(
    entity = Income::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("income_id")
), ForeignKey(
    entity = Item::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("item_id")
)))
data class IncomeItems(
    @ColumnInfo(name= "income_id") val incomeId: Int,
    @ColumnInfo(name= "item_id") val itemId: Int
)