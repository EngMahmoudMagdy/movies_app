package com.magdy.moviesapp.ui.main.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.data.dataSourcesImpl.MoviePagingSource
import com.magdy.moviesapp.domain.useCases.AddMovieToFavoritesUseCase
import com.magdy.moviesapp.domain.useCases.GetNowPlayingMoviesUseCase
import com.magdy.moviesapp.domain.useCases.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
) : CustomViewModel() {

    fun getListData(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200), pagingSourceFactory = {
            MoviePagingSource(getNowPlayingMoviesUseCase)
        }).flow.cachedIn(viewModelScope)
    }
}