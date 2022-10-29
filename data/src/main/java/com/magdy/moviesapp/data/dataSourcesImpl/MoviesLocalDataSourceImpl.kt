package com.magdy.moviesapp.data.dataSourcesImpl

import android.content.Context
import com.magdy.moviesapp.core.database.AppDatabase
import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.core.models.Movie

class MoviesLocalDataSourceImpl(context: Context) : MoviesLocalDataSource {
    private val moviesDAO = AppDatabase(context).moviesDAO()

    override suspend fun getAllFavoriteMovies(): List<Movie> = moviesDAO.getMovieList()

    override suspend fun addMovieToFavorites(movie: Movie) = moviesDAO.insertMovie(movie)

    override suspend fun removeMovieFromFavorites(movieId: Int) = moviesDAO.deleteMovie(movieId)
}