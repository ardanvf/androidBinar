package com.example.tmdb.ui.favorite

import com.example.tmdb.data.local.entity.FavoriteMovie
import com.example.tmdb.ui.shared.mapper.Mapper
import com.example.tmdb.ui.shared.model.MovieItemModel

class MovieItemMapper :
    Mapper<FavoriteMovie, MovieItemModel> {

    override fun map(dataIn: FavoriteMovie): MovieItemModel {
        return MovieItemModel(
            dataIn.movieId,
            dataIn.title,
            dataIn.releaseDate,
            dataIn.backdropUrl,
            dataIn.posterUrl,
            dataIn.overview,
            dataIn.popularity,
            dataIn.voteAverage,
            dataIn.voteCount
        )
    }

}