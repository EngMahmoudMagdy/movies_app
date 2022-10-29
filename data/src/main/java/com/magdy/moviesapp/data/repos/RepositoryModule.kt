package com.magdy.moviesapp.data.repos

import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import com.magdy.moviesapp.domain.repos.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun getMoviesRepo(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepo = MoviesRepoImpl(moviesRemoteDataSource, moviesLocalDataSource)
}