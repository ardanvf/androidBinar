package com.example.tmdb.ui.favorite

import com.example.tmdb.data.repository.MovieDbRepository
import com.example.tmdb.helper.BaseTest
import com.example.tmdb.helper.MockData
import com.example.tmdb.helper.MockData.times
import com.example.tmdb.helper.TestHelper.getMockObserver
import com.example.tmdb.ui.shared.model.MovieItemModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoritesViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: MovieDbRepository

    @MockK
    private lateinit var mockMapper: MovieItemMapper
    private lateinit var viewModel: FavoritesViewModel


    @Test
    fun `cek favorit kosong`() {
        // When
        coEvery { mockRepo.getFavoriteMovies() } returns flowOf(emptyList())
        every { mockMapper.map(any()) } returns MockData.movieItemModel
        viewModel = FavoritesViewModel(mockRepo, mockMapper)

        val observer = getMockObserver<List<MovieItemModel>>()
        viewModel.favorites.observeForever(observer)

        // Then
        Assert.assertEquals(
            emptyList<MovieItemModel>(),
            viewModel.favorites.value
        )
    }

    @Test
    fun `cek favorite ada isi`() {
        coEvery { mockRepo.getFavoriteMovies() } returns MockData.flowFavoriteMovies
        every { mockMapper.map(any()) } returns MockData.movieItemModel
        viewModel = FavoritesViewModel(mockRepo, mockMapper)

        val observer = getMockObserver<List<MovieItemModel>>()
        viewModel.favorites.observeForever(observer)

        Assert.assertEquals(
            MockData.movieItemModel.times(MockData.favoriteMovies.size),
            viewModel.favorites.value
        )
    }

}