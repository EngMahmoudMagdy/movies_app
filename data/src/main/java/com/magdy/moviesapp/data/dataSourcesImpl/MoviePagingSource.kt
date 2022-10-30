package com.magdy.moviesapp.data.dataSourcesImpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.domain.useCases.GetNowPlayingMoviesUseCase
import com.magdy.moviesapp.domain.useCases.GetResultMoviesFromSearchUseCase
import com.magdy.moviesapp.domain.useCases.GetTopRatedMoviesUseCase
import kotlinx.coroutines.flow.Flow

class MoviePagingSource(
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase? = null,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase? = null,
    val getResultMoviesFromSearchUseCase: GetResultMoviesFromSearchUseCase? = null,
    val searchName: String? = null,
) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        var loadResult: LoadResult<Int, Movie> = LoadResult.Invalid()
        val nextPage = params.key ?: FIRST_PAGE_INDEX
        val response = getCorrespondingUseCase(nextPage)
        var nextPageNumber: Int
        response.collect { responseBody ->
            when (responseBody) {
                is NetworkResult.Success -> {
                    nextPageNumber = (responseBody.data?.page ?: FIRST_PAGE_INDEX) + 1
                    loadResult = LoadResult.Page(responseBody.data?.results!!, null, nextPageNumber)
                }
                is NetworkResult.Error -> {
                    loadResult = LoadResult.Error(Throwable(responseBody.message))
                }
                else -> {
                    loadResult = LoadResult.Invalid()
                }
            }

        }
        return loadResult
    }

    private suspend fun getCorrespondingUseCase(nextPage: Int): Flow<NetworkResult<MoviesResponse>> {
        return getNowPlayingMoviesUseCase?.invoke(nextPage)
            ?: (getTopRatedMoviesUseCase?.invoke(nextPage)
                ?: getResultMoviesFromSearchUseCase?.invoke(searchName ?: "", nextPage)!!)
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}