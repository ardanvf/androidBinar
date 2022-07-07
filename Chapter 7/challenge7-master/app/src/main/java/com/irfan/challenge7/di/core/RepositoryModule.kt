package com.irfan.challenge7.di.core

import com.irfan.challenge7.data.MovieRepository
import com.irfan.challenge7.data.MovieRepositoryImpl
import com.irfan.challenge7.data.UserRepository
import com.irfan.challenge7.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}