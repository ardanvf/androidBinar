package com.example.challenge6

import android.app.Application
import com.example.challenge6.data.di.roomvmodel
import com.example.challenge6.data.di.userDB
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(userDB, roomvmodel)

        }
    }

}