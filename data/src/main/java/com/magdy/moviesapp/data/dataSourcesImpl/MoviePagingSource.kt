package com.magdy.moviesapp.data.dataSourcesImpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.domain.repos.MoviesRepo
/*

class MoviePagingSource(val moviesRepo: MoviesRepo) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val response  = moviesRepo.getNowPlayingMovies(nextPage)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}*/
