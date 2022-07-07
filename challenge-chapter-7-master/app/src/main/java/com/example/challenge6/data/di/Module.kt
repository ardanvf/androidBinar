package com.example.challenge6.data.di

import android.app.Application
import androidx.room.Room
import com.example.challenge6.data.UserDatabase
import com.example.challenge6.data.UserViewModel
import com.example.challenge6.data.dao.UserDao
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