package com.example.challenge6.data

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MovieRepositoryTest {

    @MockK
    private lateinit var service: ApiService

    @MockK
    private lateinit var repo: MovieRepository

    @Before
    fun setUp() {
        service = mockk()
        repo = MovieRepository()
    }

    @Test
    fun retrieveDetailMovie(): Unit = runBlocking {
        val getMovie = mockk<MovieRepository>()

        every {
            runBlocking { }
        }
    }
}