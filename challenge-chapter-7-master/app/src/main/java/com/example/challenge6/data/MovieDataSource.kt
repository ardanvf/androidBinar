package com.example.challenge6.data

interface MovieDataSource {

    fun retrieveMovie(callback: OperationCallback)
    fun retrieveDetailMovie(callback: OperationCallback, id_movie: String)
    fun cancel()

}