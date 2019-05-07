package com.miguel.android.trackyourexpenses.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miguel.android.trackyourexpenses.database.entity.Accounts

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE user_id = :id")
    fun getAllAccounts(id: Int): LiveData<List<Accounts>>

    @Insert
    fun addNewAccount(account: Accounts)
}