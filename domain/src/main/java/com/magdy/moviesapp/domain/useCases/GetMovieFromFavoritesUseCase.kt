package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import javax.inject.Inject

class GetMovieFromFavoritesUseCase @Inject constructor(val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieId: Int) = moviesRepo.getMovieFromFavoriteById(movieId)
}