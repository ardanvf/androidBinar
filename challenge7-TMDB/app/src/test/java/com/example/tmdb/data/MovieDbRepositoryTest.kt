package com.example.tmdb.data

import com.example.tmdb.data.local.FavoritesDao
import com.example.tmdb.data.local.entity.FavoriteMovie
import com.example.tmdb.data.remote.MovieServiceApi
import com.example.tmdb.data.repository.MovieDbRepository
import com.example.tmdb.data.repository.MovieDbRepositoryImpl
import com.example.tmdb.helper.BaseTest
import com.example.tmdb.helper.MockData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieDbRepositoryTest : BaseTest() {

    @MockK
    private lateinit var mockServiceApi: MovieServiceApi

    @MockK
    private lateinit var mockFavoritesDao: FavoritesDao

    private lateinit var repo: MovieDbRepository

    override fun setup() {
        super.setup()
        repo = MovieDbRepositoryImpl(mockServiceApi, mockFavoritesDao)
    }

    @Test
    fun `Get popular movies`() {
        // Given
        val response = Response.success(MockData.movieListResponse)
        coEvery { mockServiceApi.getPopularMovies() } returns response

        runBlockingTest {
            // When
            val result = repo.getPopularMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get movie review`() {
        // Given
        val response = Response.success(MockData.movieReviewResponse)
        coEvery { mockServiceApi.getMovieReviews(any()) } returns response

        runBlockingTest {
            // When
            val result = repo.getMovieReviews("123")
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get favorite movie dari mock `() {
        // Given
        val response = flowOf(MockData.favoriteMovie)
        every { mockFavoritesDao.getFavoriteById(any()) } returns response

        // When
        val result = repo.getFavoritedMovieById(123)

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite dari id asli`() {
        // Given
        val response = flowOf<FavoriteMovie?>(null)
        every { mockFavoritesDao.getFavoriteById(any()) } returns response

        // When
        val result = repo.getFavoritedMovieById(123)

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite movies dari mock`() {
        // Given
        val response = MockData.flowFavoriteMovies
        every { mockFavoritesDao.getFavoriteMovies() } returns response

        // When
        val result = repo.getFavoriteMovies()

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite movies asli`() {
        // Given
        val response = flowOf<List<FavoriteMovie>>(emptyList())
        every { mockFavoritesDao.getFavoriteMovies() } returns response

        // When
        val result = repo.getFavoriteMovies()

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Add favorite movie`() {
        // Given
        val input = MockData.favoriteMovie
        coEvery { mockFavoritesDao.insert(any()) } coAnswers { println("Inserting $input") }

        runBlockingTest {
            // When
            repo.addFavoriteMovie(input)

            // Then
            coVerify { mockFavoritesDao.insert(input) }
        }
    }

    @Test
    fun `Remove favorite movie`() {
        // Given
        val input = MockData.favoriteMovie
        coEvery { mockFavoritesDao.delete(any()) } coAnswers { println("Removing $input") }

        runBlockingTest {
            // When
            repo.removeFavoriteMovie(input)

            // Then
            coVerify { mockFavoritesDao.delete(input.movieId) }
        }
    }


}