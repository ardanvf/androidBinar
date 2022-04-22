package com.example.challenge.Network

import com.example.challenge.Api.Movie
import com.example.challenge.Api.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    fun getMovie(
        @Query("api_key") api_key: String = "a979b2baa3b253224f0fe88998db0444",
    ): Call<MovieResponse>

    @GET("/3/movie/{id}")
    fun getMovieId(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String = "a979b2baa3b253224f0fe88998db0444",
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>
}