package com.irfan.challenge7.data.source.local

import com.irfan.challenge7.data.source.local.entity.MovieEntity
import com.irfan.challenge7.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        if (newState) {
            movieEntity.isFavorite = newState
            movieDao.insertMovie(movieEntity)
        } else {
            movieDao.deleteMovie(movieEntity.id)
        }
    }

    suspend fun deleteAllMovie() = movieDao.deleteAll()
}