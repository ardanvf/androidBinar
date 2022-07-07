package com.example.challenge6.data

import com.example.challenge6.BuildConfig.BASE_URL
import com.example.challenge6.data.response.Movie
import com.example.challenge6.data.response.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiService {

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(): ServicesApiInterface? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {
        @GET("movie/popular")
        fun movies(
            @Query("api_key") apiKey: String, @Query("language") language: String, @Query(
                "page"
            ) page: String
        ): Call<MovieResponse>


        @GET("movie/{movie_id}")
        fun detailMovies(
            @Path("movie_id") idMovie: String, @Query("api_key") apiKey: String, @Query(
                "language"
            ) language: String
        ): Call<Movie>
    }
}
