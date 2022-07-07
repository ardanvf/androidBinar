package com.example.tmdb.ui.detail.mapper

import com.example.tmdb.data.local.entity.FavoriteMovie
import com.example.tmdb.ui.shared.mapper.Mapper
import com.example.tmdb.ui.shared.model.MovieItemModel

class FavoriteItemMapper :
    Mapper<MovieItemModel, FavoriteMovie> {

    override fun map(dataIn: MovieItemModel): FavoriteMovie {
        return FavoriteMovie(
            dataIn.id,
            dataIn.title,
            dataIn.posterPath,
            dataIn.backdropPath,
            dataIn.releaseDate,
            dataIn.overview,
            dataIn.popularity,
            dataIn.voteAverage,
            dataIn.voteCount
        )
    }

}