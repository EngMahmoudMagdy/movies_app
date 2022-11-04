package com.magdy.moviesapp.ui.details.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magdy.moviesapp.core.models.Genres
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.domain.useCases.AddMovieToFavoritesUseCase
import com.magdy.moviesapp.domain.useCases.GetMovieByIdUseCase
import com.magdy.moviesapp.domain.useCases.GetMovieFromFavoritesUseCase
import com.magdy.moviesapp.domain.useCases.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val getMovieFromFavoritesUseCase: GetMovieFromFavoritesUseCase,
    val getMovieByIdUseCase: GetMovieByIdUseCase,
    val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase
) : ViewModel() {

    val toggleFavorite: (Movie) -> Unit = { movie ->
        if (isFavorite) removeMovieFromFavorites(movie.id) else addMovieToFavorites(movie)
        isFavorite = !isFavorite
    }
    var isFavorite by mutableStateOf(false)

    val genres = mutableStateListOf<Genres>()

    fun checkIfMovieIsFavorite(movieId: Int) {
        viewModelScope.launch {
            val movie = getMovieFromFavoritesUseCase(movieId)
            isFavorite = movie != null
        }
    }

    fun addMovieToFavorites(movie: Movie) {
        viewModelScope.launch {
            addMovieToFavoritesUseCase(movie)
        }
    }

    fun removeMovieFromFavorites(movieId: Int) {
        viewModelScope.launch {
            removeMovieFromFavoritesUseCase(movieId)
        }
    }

    fun retrieveGenresFromBackend(movieId: Int) {
        viewModelScope.launch {
            val flow = getMovieByIdUseCase(movieId)
            flow.collect {
                if (it is NetworkResult.Success) {
                    genres.clear()
                    genres.addAll(it.data?.genres ?: emptyList<Genres>())
                    Log.v("Movies", it.data?.toString() ?: "")
                }
            }
        }
    }

}