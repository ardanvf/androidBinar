package com.example.tmdb.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tmdb.data.repository.MovieDbRepository
import kotlinx.coroutines.flow.map

class FavoritesViewModel(
    repo: MovieDbRepository,
    private val movieItemMapper: MovieItemMapper
) : ViewModel() {

    val favorites = repo.getFavoriteMovies()
        .map { it.map { movieItemMapper.map(it) } }
        .asLiveData()

}