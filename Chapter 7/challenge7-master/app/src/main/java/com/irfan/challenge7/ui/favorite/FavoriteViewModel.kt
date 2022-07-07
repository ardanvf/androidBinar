package com.irfan.challenge7.ui.favorite

import androidx.lifecycle.*
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.domain.usecase.favorite.GetFavoriteMovieUseCase
import com.irfan.challenge7.domain.usecase.favorite.SetFavoriteMovieUseCase
import com.irfan.challenge7.utils.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(
    val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    val setFavoriteMovieUseCase: SetFavoriteMovieUseCase
) : ViewModel() {

    private val _favoriteMovies = getFavoriteMovieUseCase().asLiveData()
    val favoriteMovies get() = _favoriteMovies

    init {
        _favoriteMovies
    }

    fun setFavoriteTourism(movie: Movie, newStatus: Boolean) {
        return setFavoriteMovieUseCase(movie, newStatus)
    }

    private val _isFavoriteMovie: MutableLiveData<Boolean> = MutableLiveData()
    val isFavoriteMovie get() = _isFavoriteMovie

    fun isFavoriteMovie(movieId: Long) {
        viewModelScope.launch {
            getFavoriteMovieUseCase().collect {
                if (it.isNullOrEmpty()) _isFavoriteMovie.postValue(false)
                it.map { movie ->
                    if (movie.id == movieId) {
                        _isFavoriteMovie.postValue(true)
                    } else {
                        _isFavoriteMovie.postValue(false)
                    }

                }
            }
        }
    }

    private val _navigateToDetail = MutableLiveData<Event<Movie>>()
    val navigateToDetail: LiveData<Event<Movie>>
        get() = _navigateToDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToDetail.value = Event(movie)
    }
}