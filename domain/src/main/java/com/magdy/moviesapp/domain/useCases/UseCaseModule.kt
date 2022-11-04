package com.magdy.moviesapp.domain.useCases

import com.magdy.moviesapp.domain.repos.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideAddMovieToFavoriteUseCase(moviesRepo: MoviesRepo) =
        AddMovieToFavoritesUseCase(moviesRepo)

    @Provides
    fun provideGetAllFavoriteMoviesUseCase(moviesRepo: MoviesRepo) =
        GetAllFavoriteMoviesUseCase(moviesRepo)

    @Provides
    fun provideGetNowPlayingMoviesUseCase(moviesRepo: MoviesRepo) =
        GetNowPlayingMoviesUseCase(moviesRepo)

    @Provides
    fun provideGetResultMoviesFromSearchUseCase(moviesRepo: MoviesRepo) =
        GetResultMoviesFromSearchUseCase(moviesRepo)

    @Provides
    fun provideGetMovieByIdUseCase(moviesRepo: MoviesRepo) =
        GetMovieByIdUseCase(moviesRepo)

    @Provides
    fun provideGetMovieFromFavoritesUseCase(moviesRepo: MoviesRepo) =
        GetMovieFromFavoritesUseCase(moviesRepo)

    @Provides
    fun provideGetTopRatedMoviesUseCase(moviesRepo: MoviesRepo) =
        GetTopRatedMoviesUseCase(moviesRepo)

    @Provides
    fun provideRemoveMovieFromFavoritesUseCase(moviesRepo: MoviesRepo) =
        RemoveMovieFromFavoritesUseCase(moviesRepo)
}