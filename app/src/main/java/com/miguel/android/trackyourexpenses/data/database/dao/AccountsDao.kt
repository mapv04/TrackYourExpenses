package com.miguel.android.trackyourexpenses.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE userId = :id")
    fun getAllAccounts(id: Int): LiveData<List<Accounts>>

    @Insert
    suspend fun addNewAccount(account: Accounts)

    @Delete
    fun deleteAccount(account: Accounts)
}