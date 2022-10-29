package com.magdy.moviesapp.data.dataSourcesImpl

import com.magdy.moviesapp.core.services.ApiService
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MoviesRemoteDataSourceImpl @Inject constructor( val apiService: ApiService) :
    MoviesRemoteDataSource {
    override suspend fun getNowPlayingMovies(page: Int) =
        apiService.getNowPlayingMovies(page = page)

    override suspend fun getTopRatedMovies(page: Int) =
        apiService.getTopRatedMovies(page = page)

    override suspend fun getMoviesFromSearch(movieName: String, page: Int) =
        apiService.getResultMoviesFromSearch(search = movieName, page = page)
}