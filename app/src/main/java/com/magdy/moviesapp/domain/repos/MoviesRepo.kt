package com.magdy.moviesapp.domain.repos

interface MoviesRepo {
    suspend fun getNowPlayingMovies()
    suspend fun getTopRatedMovies()
    suspend fun getResultMoviesFromSearch(movieName:String)
}