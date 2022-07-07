package com.example.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.repository.MovieDbRepository
import com.example.tmdb.ui.detail.mapper.FavoriteItemMapper
import com.example.tmdb.ui.detail.mapper.ReviewListMapper
import com.example.tmdb.ui.shared.model.MovieItemModel
import com.example.tmdb.ui.utils.ActionStateLiveData
import com.example.tmdb.ui.utils.LiveEvent
import com.example.tmdb.ui.utils.MutableLiveEvent
import com.example.tmdb.ui.utils.postEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo: MovieDbRepository,
    reviewListMapper: ReviewListMapper,
    private val favoriteItemMapper: FavoriteItemMapper
) : ViewModel() {

    lateinit var movieItem: MovieItemModel

    private val _isFavorite = MutableLiveData(false)
    val isFavorite = _isFavorite as LiveData<Boolean>

    private val _snackbar = MutableLiveEvent<String>()
    val snackbar = _snackbar as LiveEvent<String>

    val reviews = ActionStateLiveData(viewModelScope, reviewListMapper) {
        repo.getMovieReviews(movieItem.id.toString())
    }

    /**
     * Entry Point
     */
    fun init(movieItem: MovieItemModel) {
        this.movieItem = movieItem
    }

    fun onFavoriteClick() {
        if (_isFavorite.value == true) {
            removeFromFavorite()
        } else {
            addToFavorite()
        }
    }

    fun checkIsFavorite() {
        viewModelScope.launch {
            repo.getFavoritedMovieById(movieItem.id).collect {
                _isFavorite.postValue(it != null)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            repo.addFavoriteMovie(favoriteItemMapper.map(movieItem))
            _snackbar.postEvent("Added to favorite")
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            repo.removeFavoriteMovie(favoriteItemMapper.map(movieItem))
            _snackbar.postEvent("Removed from favorite")
        }
    }
}