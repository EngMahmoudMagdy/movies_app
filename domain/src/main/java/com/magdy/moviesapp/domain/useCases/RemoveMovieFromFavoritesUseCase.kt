package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class RemoveMovieFromFavoritesUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieId: Int) = moviesRepo.removeMovieFromFavorites(movieId)
}