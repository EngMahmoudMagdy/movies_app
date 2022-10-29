package com.magdy.moviesapp.domain.repos

import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import kotlinx.coroutines.flow.Flow


interface MoviesRepo {
    suspend fun getNowPlayingMovies(page:Int): Flow<NetworkResult<MoviesResponse>>
    suspend fun getTopRatedMovies(page:Int): Flow<NetworkResult<MoviesResponse>>
    suspend fun getResultMoviesFromSearch(movieName:String,page:Int): Flow<NetworkResult<MoviesResponse>>
    suspend fun getAllFavoriteMovies(): List<com.magdy.moviesapp.core.models.Movie>
    suspend fun addMovieToFavorites(movie: com.magdy.moviesapp.core.models.Movie)
    suspend fun removeMovieFromFavorites(movieId: Int)
}