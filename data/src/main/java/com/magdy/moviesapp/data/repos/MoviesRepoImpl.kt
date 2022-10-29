package com.magdy.moviesapp.data.repos

import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.domain.repos.MoviesRepo

class MoviesRepoImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
) : MoviesRepo {
    override suspend fun getNowPlayingMovies() = moviesRemoteDataSource.getNowPlayingMovies()

    override suspend fun getTopRatedMovies() = moviesRemoteDataSource.getTopRatedMovies()

    override suspend fun getResultMoviesFromSearch(movieName: String)  = moviesRemoteDataSource.getMoviesFromSearch(movieName)

    override suspend fun getAllFavoriteMovies(): List<Movie> = moviesLocalDataSource.getAllFavoriteMovies()

    override suspend fun addMovieToFavorites(movie: Movie) = moviesLocalDataSource.addMovieToFavorites(movie)

    override suspend fun removeMovieFromFavorites(movieId: Int) = moviesLocalDataSource.removeMovieFromFavorites(movieId)
}