package com.example.challenge6.data.response

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("vote_average") val vote_average : Double,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("original_language") val original_language : String
)