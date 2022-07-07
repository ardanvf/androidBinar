package com.example.tmdb.core.di

import com.example.tmdb.ui.detail.detailModule
import com.example.tmdb.ui.favorite.favoritesModule
import com.example.tmdb.ui.home.homeModule

val uiModule = listOf(
    homeModule,
    detailModule,
    favoritesModule
)