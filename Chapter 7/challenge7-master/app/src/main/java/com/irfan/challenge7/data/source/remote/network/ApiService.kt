package com.irfan.challenge7.data.source.remote.network

import com.irfan.challenge7.BuildConfig
import com.irfan.challenge7.data.source.remote.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("languange") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PopularMovieResponse
}
