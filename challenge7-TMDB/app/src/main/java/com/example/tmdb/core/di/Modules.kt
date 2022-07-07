package com.example.tmdb

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.dao.UserDao
import com.example.tmdb.data.dao.UserDatabase
import com.example.tmdb.data.dao.UserViewModel

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


    val userDB = module {
        fun provideDataBase(application: Application): UserDatabase {
            return Room.databaseBuilder(application, UserDatabase::class.java, "item_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideDao(dataBase: UserDatabase): UserDao {
            return dataBase.daoLogin()
        }
        single { provideDataBase(androidApplication()) }
        single { provideDao(get()) }

    }
    val  roomvmodel = module{
        viewModel {
            UserViewModel(get())
        }
    }

