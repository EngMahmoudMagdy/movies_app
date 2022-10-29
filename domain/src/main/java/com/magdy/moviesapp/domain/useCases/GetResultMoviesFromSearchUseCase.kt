package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import javax.inject.Inject

class GetResultMoviesFromSearchUseCase @Inject constructor(val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieName: String,page:Int) = moviesRepo.getResultMoviesFromSearch(movieName,page)
}