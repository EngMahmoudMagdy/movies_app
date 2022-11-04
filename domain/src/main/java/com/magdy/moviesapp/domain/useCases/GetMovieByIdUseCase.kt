package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(val moviesRepo: MoviesRepo) : ListUseCase {
    suspend operator fun invoke(movieId: Int) = moviesRepo.getMovieById(movieId)
}