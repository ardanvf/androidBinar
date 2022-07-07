package com.example.tmdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.repository.MovieDbRepository
import com.example.tmdb.ui.utils.ActionStateLiveData

class HomeViewModel(
    private val repo: MovieDbRepository,
    movieListMapper: MovieListMapper
) : ViewModel() {

    val popularMovies = ActionStateLiveData(viewModelScope, movieListMapper) {
        repo.getPopularMovies()
    }
}