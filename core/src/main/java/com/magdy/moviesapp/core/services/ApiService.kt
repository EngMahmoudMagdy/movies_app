package com.magdy.moviesapp.core.services

import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun getResultMoviesFromSearch(
        @Query("query") search: String,
        @Query("page") page: Int,
    ): Response<MoviesResponse>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
    ): Response<Movie>
}
