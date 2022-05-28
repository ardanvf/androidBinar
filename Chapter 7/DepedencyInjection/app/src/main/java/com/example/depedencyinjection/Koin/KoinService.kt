package com.example.depedencyinjection.Koin

import org.koin.dsl.module

interface KoinService {
    fun provideService(): String
}