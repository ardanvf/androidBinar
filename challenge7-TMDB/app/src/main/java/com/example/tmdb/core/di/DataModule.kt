package com.example.tmdb.core.di

import com.example.tmdb.data.local.persistenceModule
import com.example.tmdb.data.remote.networkModule
import com.example.tmdb.data.repository.repositoryModule

val dataModule = listOf(
    networkModule,
    persistenceModule,
    repositoryModule
)