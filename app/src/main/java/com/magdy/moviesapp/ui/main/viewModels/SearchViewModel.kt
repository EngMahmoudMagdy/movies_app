package com.magdy.moviesapp.ui.main.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.magdy.moviesapp.data.dataSourcesImpl.MoviePagingSource
import com.magdy.moviesapp.domain.useCases.AddMovieToFavoritesUseCase
import com.magdy.moviesapp.domain.useCases.GetResultMoviesFromSearchUseCase
import com.magdy.moviesapp.domain.useCases.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    val getResultMoviesFromSearchUseCase: GetResultMoviesFromSearchUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
) : CustomViewModel() {
    private val postListUrlFlow = MutableStateFlow("")
    val postList = postListUrlFlow.flatMapLatest { query ->
        Pager(PagingConfig(pageSize = 20))
        {
            MoviePagingSource(
                getResultMoviesFromSearchUseCase = getResultMoviesFromSearchUseCase,
                searchName = query
            )
        }.flow.onStart { emit(PagingData.empty()) }.cachedIn(viewModelScope)
    }


    suspend fun getListData(query: String) {
        postListUrlFlow.emit(query)
    }
}
