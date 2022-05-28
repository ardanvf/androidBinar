package com.example.challenge.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challenge.Api.Movie
import com.example.challenge.Api.MovieResponse
import com.example.challenge.Network.ApiInterface
import com.example.challenge.Network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel:ViewModel() {

    fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = ApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}