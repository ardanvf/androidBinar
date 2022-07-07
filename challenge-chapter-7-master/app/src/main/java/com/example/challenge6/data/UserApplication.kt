package com.example.challenge6.data

import android.app.Application

class UserApplication : Application() {

    val database by lazy { UserDatabase.getDatabase(this) }
}