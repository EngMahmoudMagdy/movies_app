package com.magdy.moviesapp.data.dataSourcesImpl

import com.magdy.moviesapp.core.database.MoviesDAO
import com.magdy.moviesapp.core.services.ApiService
import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class DataSourceModule {

    @Provides
    fun getMoviesLocalDataSource(moviesDAO: MoviesDAO): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(moviesDAO)

    @Provides
    fun getMoviesRemoteDataSource(apiService: ApiService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(apiService)
}