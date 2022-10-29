package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class GetNowPlayingMoviesUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke() = moviesRepo.getNowPlayingMovies()
}