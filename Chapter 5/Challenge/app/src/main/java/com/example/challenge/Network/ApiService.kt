package com.example.challenge.Network

import android.graphics.pdf.PdfDocument
import com.example.challenge.Api.MovieList
import retrofit2.Call
import retrofit2.http.GET
import com.example.challenge.Api.Result
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/3/movie/popular")
    fun getMovie(
        @Query("api_key") api_key: String = "a979b2baa3b253224f0fe88998db0444",
        @Query("page") page: Int = 1
    ): Call<List<Result>>

}