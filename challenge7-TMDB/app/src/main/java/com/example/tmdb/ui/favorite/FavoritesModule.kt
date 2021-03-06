package com.example.tmdb.ui.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    single { MovieItemMapper() }
    viewModel { FavoritesViewModel(get(), get()) }
}