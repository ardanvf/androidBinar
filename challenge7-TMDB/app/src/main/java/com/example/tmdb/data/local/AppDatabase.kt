package com.example.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdb.data.local.entity.FavoriteMovie

@Database(version = 1, exportSchema = true, entities = [FavoriteMovie::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoritesDao

}