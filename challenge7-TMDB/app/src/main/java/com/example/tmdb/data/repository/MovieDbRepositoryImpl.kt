package com.example.tmdb.data.repository

import com.example.tmdb.data.local.FavoritesDao
import com.example.tmdb.data.local.entity.FavoriteMovie
import com.example.tmdb.data.remote.MovieServiceApi
import com.example.tmdb.data.remote.model.MovieListResponse
import com.example.tmdb.data.remote.model.MovieReviewResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieDbRepositoryImpl(
    private val serviceApi: MovieServiceApi,
    private val favoriteDao: FavoritesDao
) : MovieDbRepository {

    override suspend fun getPopularMovies(): Response<MovieListResponse> {
        return serviceApi.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieListResponse> {
        return serviceApi.getTopRatedMovies()
    }

    override suspend fun getNowPlayingMovies(): Response<MovieListResponse> {
        return serviceApi.getNowPlayingMovies()
    }

    override suspend fun getMovieReviews(movieId: String): Response<MovieReviewResponse> {
        return serviceApi.getMovieReviews(movieId)
    }

    override fun getFavoritedMovieById(id: Int): Flow<FavoriteMovie?> {
        return favoriteDao.getFavoriteById(id)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return favoriteDao.getFavoriteMovies()
    }

    override suspend fun addFavoriteMovie(movie: FavoriteMovie) {
        favoriteDao.insert(movie)
    }

    override suspend fun removeFavoriteMovie(movie: FavoriteMovie) {
        favoriteDao.delete(movie.movieId)
    }

}