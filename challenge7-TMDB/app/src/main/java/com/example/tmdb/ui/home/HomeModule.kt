package com.example.tmdb.ui.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { MovieListMapper() }
    viewModel { HomeViewModel(get(), get()) }
}