package com.irfan.challenge7.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: String,
    var isFavorite: Boolean = false
) : Parcelable {
    fun posterPathUrl() = "https://image.tmdb.org/t/p/w200/$posterPath"

    fun backdropPathUrl() = "https://image.tmdb.org/t/p/w300/$backdropPath"
}
