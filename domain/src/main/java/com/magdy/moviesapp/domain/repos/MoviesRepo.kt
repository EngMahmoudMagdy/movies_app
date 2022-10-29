package com.magdy.moviesapp.domain.repos


interface MoviesRepo {
    suspend fun getNowPlayingMovies(): List<com.magdy.moviesapp.core.models.Movie>
    suspend fun getTopRatedMovies(): List<com.magdy.moviesapp.core.models.Movie>
    suspend fun getResultMoviesFromSearch(movieName:String): List<com.magdy.moviesapp.core.models.Movie>
    suspend fun getAllFavoriteMovies(): List<com.magdy.moviesapp.core.models.Movie>
    suspend fun addMovieToFavorites(movie: com.magdy.moviesapp.core.models.Movie)
    suspend fun removeMovieFromFavorites(movieId: Int)
}