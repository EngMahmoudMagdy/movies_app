package com.magdy.moviesapp.data.dataSourcesImpl

import com.magdy.moviesapp.core.database.MoviesDAO
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MoviesLocalDataSourceImpl @Inject constructor(val moviesDAO: MoviesDAO) :
    MoviesLocalDataSource {

    override suspend fun getAllFavoriteMovies(): List<Movie> = moviesDAO.getMovieList()

    override suspend fun addMovieToFavorites(movie: Movie) = moviesDAO.insertMovie(movie)

    override suspend fun removeMovieFromFavorites(movieId: Int) = moviesDAO.deleteMovie(movieId)
}