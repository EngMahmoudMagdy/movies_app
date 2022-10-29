package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class GetResultMoviesFromSearchUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieName: String) = moviesRepo.getResultMoviesFromSearch(movieName)
}