package com.irfan.challenge7.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfan.challenge7.data.source.local.entity.MovieEntity
import com.irfan.challenge7.data.source.local.entity.UserEntity

@Database(
    entities = [MovieEntity::class, UserEntity::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun userDao(): UserDao
}