package com.miguel.android.trackyourexpenses.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String)