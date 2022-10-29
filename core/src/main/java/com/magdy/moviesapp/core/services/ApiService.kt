package com.magdy.moviesapp.core.services

import com.magdy.moviesapp.core.services.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("search")
    suspend fun getResultMoviesFromSearch(
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<MoviesResponse>
}
