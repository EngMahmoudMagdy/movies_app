package com.magdy.moviesapp.data.dataSources

import com.magdy.moviesapp.core.models.Movie

interface MoviesRemoteDataSource {

    suspend fun getNowPlayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getMoviesFromSearch(movieName: String): List<Movie>

}