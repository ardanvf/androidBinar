package com.irfan.challenge7.mapper

import com.irfan.challenge7.data.source.local.entity.MovieEntity
import com.irfan.challenge7.data.source.remote.response.MovieResponse
import com.irfan.challenge7.domain.model.Movie

object Mapper {

    fun mapDomainToEntity(movie: Movie) = MovieEntity(
        movie.id,
        movie.originalTitle,
        movie.overview,
        movie.posterPath,
        movie.backdropPath,
        movie.releaseDate,
        movie.title,
        movie.voteAverage,
        movie.isFavorite
    )

    fun mapResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                it.id,
                it.originalTitle,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.title,
                it.voteAverage,
                false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.originalTitle,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.title,
                it.voteAverage,
                it.isFavorite
            )
        }
}