package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(val moviesRepo: MoviesRepo) :ListUseCase{
    suspend operator fun invoke(page:Int) = moviesRepo.getNowPlayingMovies(page)
}