package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(page: Int) = moviesRepo.getTopRatedMovies(page)
}