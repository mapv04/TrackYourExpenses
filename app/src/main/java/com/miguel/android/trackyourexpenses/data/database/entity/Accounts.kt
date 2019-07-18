package com.miguel.android.trackyourexpenses.data.database.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "accounts", foreignKeys = arrayOf(ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId")
)))
data class Accounts(
    @PrimaryKey
    val id: String,
    val name: String,
    val color: Int?,
    val imageLocation: String?,
    val lastUpdate: String,
    @ColumnInfo(index = true)
    val userId: String
): Parcelable