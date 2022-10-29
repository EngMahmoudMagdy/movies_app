package com.magdy.moviesapp.data.dataSources

import com.magdy.moviesapp.core.models.Movie

interface MoviesLocalDataSource {

    suspend fun getAllFavoriteMovies(): List<Movie>

    suspend fun addMovieToFavorites(movie: Movie)

    suspend fun removeMovieFromFavorites(movieId: Int)
}