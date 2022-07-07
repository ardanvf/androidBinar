package com.example.tmdb.ui.detail

import com.example.tmdb.ui.detail.mapper.FavoriteItemMapper
import com.example.tmdb.ui.detail.mapper.ReviewListMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    single { ReviewListMapper() }
    single { FavoriteItemMapper() }
    viewModel { DetailViewModel(get(), get(), get()) }
}