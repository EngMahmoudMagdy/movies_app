package com.magdy.moviesapp.data.repos

import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.services.responses.BaseApiResponse
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import com.magdy.moviesapp.domain.repos.MoviesRepo
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MoviesRepoImpl @Inject constructor(
    val moviesRemoteDataSource: MoviesRemoteDataSource,
    val moviesLocalDataSource: MoviesLocalDataSource,
) : MoviesRepo, BaseApiResponse() {
    override suspend fun getNowPlayingMovies(page: Int): Flow<NetworkResult<MoviesResponse>> {
        return flow {
            emit(safeApiCall {
                moviesRemoteDataSource.getNowPlayingMovies(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<NetworkResult<MoviesResponse>> {
        return flow {
            emit(safeApiCall {
                moviesRemoteDataSource.getTopRatedMovies(page)
            })
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun getResultMoviesFromSearch(
        movieName: String,
        page: Int
    ): Flow<NetworkResult<MoviesResponse>> {
        return flow {
            emit(safeApiCall {
                moviesRemoteDataSource.getMoviesFromSearch(movieName, page)
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieById(movieId: Int): Flow<NetworkResult<Movie>> {
        return flow {
            emit(safeApiCall {
                moviesRemoteDataSource.getMovieById(movieId)
            })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieFromFavoriteById(movieId: Int): Movie? = moviesLocalDataSource.getMovieFromFavorites(movieId)


    override suspend fun getAllFavoriteMovies(limit: Int, offset: Int): List<Movie> {
        return moviesLocalDataSource.getAllFavoriteMovies(limit, offset)
    }

    override suspend fun addMovieToFavorites(movie: Movie) =
        moviesLocalDataSource.addMovieToFavorites(movie)

    override suspend fun removeMovieFromFavorites(movieId: Int) =
        moviesLocalDataSource.removeMovieFromFavorites(movieId)
}