package com.example.challenge6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge6.data.dao.UserDao
import com.example.challenge6.data.entity.Login

@Database(entities = [Login::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun daoLogin(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}