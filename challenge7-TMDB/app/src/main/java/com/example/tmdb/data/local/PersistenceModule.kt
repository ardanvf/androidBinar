package com.example.tmdb.data.local

import androidx.room.Room
import com.example.tmdb.data.local.PersistenceConstant.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object PersistenceConstant {
    const val DB_NAME = "moviedbexplorer_db"
}

val persistenceModule = module {

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().favoriteMovieDao() }
}