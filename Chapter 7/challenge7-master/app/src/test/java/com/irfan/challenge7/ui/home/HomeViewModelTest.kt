package com.irfan.challenge7.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.irfan.challenge7.DataDummy
import com.irfan.challenge7.MainCoroutineRule
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRules = MainCoroutineRule()

    @Mock
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `when Get Movie Should Not Null`() = mainCoroutineRules.runBlockingTest {
        val dummyMovie = DataDummy.generateDummyMovie()
        val data = PagedTestDataSources.snapshot(dummyMovie)
        val movie = MutableLiveData<PagingData<Movie>>()
        movie.value = data
        Mockito.`when`(viewModel.popularMovies).thenReturn(movie)
        val actualMovie = viewModel.popularMovies.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MovieAdapter.MovieDiffCallBack,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = mainCoroutineRules.dispatcher,
            workerDispatcher = mainCoroutineRules.dispatcher,
        )
        differ.submitData(actualMovie)

        advanceUntilIdle()

        Mockito.verify(viewModel).popularMovies
        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyMovie.size, differ.snapshot().size)
        Assert.assertEquals(dummyMovie[0].title, differ.snapshot()[0]?.title)

    }

    class PagedTestDataSources : PagingSource<Int, LiveData<List<Movie>>>() {
        companion object {
            fun snapshot(items: List<Movie>): PagingData<Movie> {
                return PagingData.from(items)
            }
        }
        override fun getRefreshKey(state: PagingState<Int, LiveData<List<Movie>>>): Int {
            return 0
        }
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<Movie>>> {
            return LoadResult.Page(emptyList(), 0 , 1)
        }
    }

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}