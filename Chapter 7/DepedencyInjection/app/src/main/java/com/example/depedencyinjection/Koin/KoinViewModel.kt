package com.example.depedencyinjection.Koin

import androidx.lifecycle.ViewModel

class KoinViewModel(val repository: KoinRepository): ViewModel() {
    fun doingSomethingWithKoinService() = repository.provideService()
}