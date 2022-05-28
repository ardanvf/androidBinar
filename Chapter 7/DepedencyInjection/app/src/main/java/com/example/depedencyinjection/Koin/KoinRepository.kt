package com.example.depedencyinjection.Koin

class KoinRepository : KoinService {
    override fun provideService(): String = "Provide Koin Service"
}