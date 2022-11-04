package com.magdy.moviesapp.data.dataSources

import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import retrofit2.Response

interface MoviesRemoteDataSource {

    suspend fun getNowPlayingMovies(page: Int): Response<MoviesResponse>

    suspend fun getTopRatedMovies(page: Int): Response<MoviesResponse>

    suspend fun getMoviesFromSearch(movieName: String, page: Int): Response<MoviesResponse>

    suspend fun getMovieById(movieId:Int) : Response<Movie>
}