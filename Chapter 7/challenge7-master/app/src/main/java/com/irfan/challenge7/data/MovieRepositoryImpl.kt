package com.irfan.challenge7.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.irfan.challenge7.data.source.local.MovieLocalDataSource
import com.irfan.challenge7.data.source.remote.MoviePagingSource
import com.irfan.challenge7.data.source.remote.network.ApiService
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.mapper.Mapper
import com.irfan.challenge7.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepository {
    fun getPopularMovies(): LiveData<PagingData<Movie>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    suspend fun deleteAllMovie()
}

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepository {

    override fun getPopularMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService)
            }
        ).liveData
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        movieLocalDataSource.getAllMovie().map {
            Mapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = Mapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { movieLocalDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override suspend fun deleteAllMovie() = movieLocalDataSource.deleteAllMovie()
}

