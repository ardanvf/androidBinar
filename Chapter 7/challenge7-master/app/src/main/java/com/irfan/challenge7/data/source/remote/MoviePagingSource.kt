package com.irfan.challenge7.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.irfan.challenge7.data.source.remote.network.ApiService
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.mapper.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getPopularMovie(position).results

            LoadResult.Page(
                data = Mapper.mapResponsesToDomain(responseData),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }
}