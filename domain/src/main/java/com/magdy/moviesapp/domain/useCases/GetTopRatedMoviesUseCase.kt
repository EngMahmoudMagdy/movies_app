package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class GetTopRatedMoviesUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke() = moviesRepo.getTopRatedMovies()
}