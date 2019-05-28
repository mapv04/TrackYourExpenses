package com.miguel.android.trackyourexpenses.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.database.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun authentication(username: String, password: String): User

    @Query("SELECT COUNT(username) FROM users WHERE :username = username")
    fun checkIfExists(username: String?): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewUser(user: User)

    @Query("SELECT * FROM accounts WHERE user_id = :id")
    fun getAllAccountsByUserId(id: Int): Accounts?
}