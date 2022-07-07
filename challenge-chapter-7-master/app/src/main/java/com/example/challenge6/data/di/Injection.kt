package com.example.challenge6.data.di

import com.example.challenge6.data.MovieDataSource
import com.example.challenge6.data.MovieRepository

object Injection {

    fun providerRepository(): MovieDataSource {
        return MovieRepository()
    }
}