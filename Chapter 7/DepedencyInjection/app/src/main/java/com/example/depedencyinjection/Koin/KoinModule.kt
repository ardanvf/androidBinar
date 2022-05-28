package com.example.depedencyinjection.Koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.Koin
import org.koin.dsl.module

val koinModule = module {
    single<KoinRepository>{
        KoinRepository()
    }

    viewModel { KoinViewModel(get()) }
}

val anotherModule = module {

}