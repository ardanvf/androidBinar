package com.example.tmdb.core

import android.app.Application
import com.example.tmdb.core.di.dataModule
import com.example.tmdb.core.di.uiModule
import com.example.tmdb.roomvmodel
import com.example.tmdb.userDB
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TMDB: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TMDB)
            modules(dataModule)
            modules(uiModule)
            modules(userDB, roomvmodel)

        }
    }

}