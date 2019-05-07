package com.miguel.android.trackyourexpenses.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miguel.android.trackyourexpenses.database.dao.AccountsDao
import com.miguel.android.trackyourexpenses.database.entity.User
import com.miguel.android.trackyourexpenses.database.dao.UserDao
import com.miguel.android.trackyourexpenses.database.entity.Accounts

@Database(entities = [User::class, Accounts::class], version = 1)
abstract class MoneyManagerRoomDB: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountsDao

companion object{
    @Volatile
    private var INSTANCE: MoneyManagerRoomDB? = null

    fun getDatabase(context: Context): MoneyManagerRoomDB {
        val tempInstance = INSTANCE
        if(tempInstance != null){
            return tempInstance
        }
        synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MoneyManagerRoomDB::class.java,
                "MoneyManager"
            ).build()
            INSTANCE = instance
            return instance
        }
    }

}
}