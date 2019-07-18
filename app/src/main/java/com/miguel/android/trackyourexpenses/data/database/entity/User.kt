package com.miguel.android.trackyourexpenses.data.database.entity

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String)