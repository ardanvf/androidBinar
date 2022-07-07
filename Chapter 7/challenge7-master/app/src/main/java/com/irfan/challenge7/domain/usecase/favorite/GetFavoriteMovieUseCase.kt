package com.irfan.challenge7.domain.usecase.favorite

import com.irfan.challenge7.data.MovieRepository
import com.irfan.challenge7.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }
}