package com.irfan.challenge7.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.domain.usecase.movie.GetPopularMovieUseCase
import com.irfan.challenge7.storage.PreferencesManager
import com.irfan.challenge7.utils.Event

class HomeViewModel(
    getPopularMovieUseCase: GetPopularMovieUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _menuItemSelectedLiveData = MutableLiveData<Event<Int>>()
    val menuItemSelectedLiveData: LiveData<Event<Int>> = _menuItemSelectedLiveData

    private val _navigateToDetail = MutableLiveData<Event<Movie>>()
    val navigateToDetail: LiveData<Event<Movie>>
        get() = _navigateToDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToDetail.value = Event(movie)
    }

    fun onMenuItemSelected(actionId: Int) {
        _menuItemSelectedLiveData.postValue(Event(actionId))
    }

    fun getUserEmail(): LiveData<String> {
        return preferencesManager.getUserEmail().asLiveData()
    }

    private val _popularMovies = getPopularMovieUseCase().cachedIn(viewModelScope)
    val popularMovies: LiveData<PagingData<Movie>> get() = _popularMovies
}