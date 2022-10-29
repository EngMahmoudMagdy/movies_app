package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo

class AddMovieToFavoritesUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movie: com.magdy.moviesapp.core.models.Movie) =
        moviesRepo.addMovieToFavorites(movie)
}