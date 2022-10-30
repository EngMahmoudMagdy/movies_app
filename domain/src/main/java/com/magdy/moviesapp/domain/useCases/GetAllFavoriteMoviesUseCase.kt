package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class GetAllFavoriteMoviesUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(limit: Int, offset: Int) = moviesRepo.getAllFavoriteMovies(limit,offset)
}