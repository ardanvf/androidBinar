package com.example.tmdb.data.repository

import org.koin.dsl.module

val repositoryModule = module {

    single<MovieDbRepository> { MovieDbRepositoryImpl(get(), get()) }

}