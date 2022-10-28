package com.magdy.moviesapp.data.repos

import com.magdy.moviesapp.domain.repos.MoviesRepo

class MoviesRepoImpl:MoviesRepo {
    override suspend fun getNowPlayingMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getResultMoviesFromSearch(movieName: String) {
        TODO("Not yet implemented")
    }
}