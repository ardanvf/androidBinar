package com.example.tmdb.ui.detail.mapper

import com.example.tmdb.data.remote.model.MovieReviewResponse
import com.example.tmdb.ui.detail.ReviewItemModel
import com.example.tmdb.ui.shared.mapper.Mapper

class ReviewListMapper : Mapper<MovieReviewResponse, List<ReviewItemModel>> {
    override fun map(dataIn: MovieReviewResponse): List<ReviewItemModel> {
        return dataIn.results.orEmpty().map {
            ReviewItemModel(
                it.id,
                it.author,
                it.content,
                it.url
            )
        }
    }

}