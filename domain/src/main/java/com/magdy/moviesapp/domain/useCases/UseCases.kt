package com.magdy.moviesapp.domain.useCases

data class UseCases(
    val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase,
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getResultMoviesFromSearchUseCase: GetResultMoviesFromSearchUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
)
