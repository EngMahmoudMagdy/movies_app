package com.magdy.moviesapp.ui.main.viewModels

import androidx.lifecycle.*
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.domain.useCases.AddMovieToFavoritesUseCase
import com.magdy.moviesapp.domain.useCases.GetNowPlayingMoviesUseCase
import com.magdy.moviesapp.domain.useCases.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel  @Inject constructor(
    val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
) : CustomViewModel() {

    private val _response: MutableLiveData<NetworkResult<MoviesResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<MoviesResponse>> = _response
    fun fetchNowPlayingResponse() = viewModelScope.launch {
        getNowPlayingMoviesUseCase(1).collect { values ->
            _response.value = values
        }
    }

    /*class NowPlayingViewModelFactory(
        val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
        val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
        val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                AddMovieToFavoritesUseCase::class.java,
                GetNowPlayingMoviesUseCase::class.java,
                RemoveMovieFromFavoritesUseCase::class.java
            ).newInstance(
                addMovieToFavoritesUseCase,
                getNowPlayingMoviesUseCase,
                removeMovieFromFavoritesUseCase,
            )
        }
    }*/
}