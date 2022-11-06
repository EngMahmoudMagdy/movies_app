package com.magdy.moviesapp.data.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.data.dataSources.MoviesLocalDataSource
import com.magdy.moviesapp.data.dataSources.MoviesRemoteDataSource
import com.magdy.moviesapp.domain.repos.MoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesRepoImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mockRemoteDataSource: MoviesRemoteDataSource

    @Mock
    lateinit var mockLocalDataSource: MoviesLocalDataSource

    lateinit var repo: MoviesRepo

    private val tMovie = Movie(
        "", "", "", false, "", "", "", "", 0.0, 0.0, 0, false, 0, null
    )

    private val tPage = 1
    private val tMovieName = "name"

    @Before
    fun setUp() {
        repo = MoviesRepoImpl(mockRemoteDataSource, mockLocalDataSource)
    }

    @Test
    fun getNowPlayingMovies() = testCoroutineRule.runTest {
        whenever(mockRemoteDataSource.getNowPlayingMovies(tPage)).thenAnswer {
            Response.success(
                MoviesResponse(
                    tPage,
                    1,
                    listOf(tMovie), 1
                )
            )
        }

        val result = repo.getNowPlayingMovies(tPage).first()

        Assert.assertEquals(result.data?.results?.get(0)?.id, tMovie.id)
    }

    @Test
    fun getTopRatedMovies() = testCoroutineRule.runTest {
        whenever(mockRemoteDataSource.getTopRatedMovies(tPage)).thenAnswer {
            Response.success(
                MoviesResponse(
                    tPage,
                    1,
                    listOf(tMovie), 1
                )
            )
        }

        val result = repo.getTopRatedMovies(tPage).first()

        Assert.assertEquals(result.data?.results?.get(0)?.id, tMovie.id)
    }

    @Test
    fun getResultMoviesFromSearch() = testCoroutineRule.runTest {
        whenever(mockRemoteDataSource.getMoviesFromSearch(tMovieName, tPage)).thenAnswer {
            Response.success(
                MoviesResponse(
                    tPage,
                    1,
                    listOf(tMovie), 1
                )
            )
        }

        val result = repo.getResultMoviesFromSearch(tMovieName, tPage).first()

        Assert.assertEquals(result.data?.results?.get(0)?.id, tMovie.id)
    }

    @Test
    fun getMovieById() = testCoroutineRule.runTest {
        whenever(mockRemoteDataSource.getMovieById(tMovie.id)).thenAnswer {
            Response.success(
                tMovie
            )
        }

        val result = repo.getMovieById(tMovie.id).first()

        Assert.assertEquals(result.data, tMovie)
        Assert.assertEquals(result.data?.id, tMovie.id)
    }

    @Test
    fun getMovieFromFavoriteById() = testCoroutineRule.runTest {
        whenever(mockLocalDataSource.getMovieFromFavorites(tMovie.id)).thenReturn(tMovie)

        val result = repo.getMovieFromFavoriteById(tMovie.id)

        Assert.assertEquals(result, tMovie)
        Assert.assertEquals(result?.id, tMovie.id)
    }

    @Test
    fun getAllFavoriteMovies() = testCoroutineRule.runTest {
        whenever(mockLocalDataSource.getAllFavoriteMovies(tPage, tPage)).thenReturn(
            listOf(tMovie)
        )

        val result = repo.getAllFavoriteMovies(tPage, tPage)

        Assert.assertEquals(result[0].id, tMovie.id)
    }

    @Test
    fun addMovieToFavorites() = testCoroutineRule.runTest {
        whenever(mockLocalDataSource.addMovieToFavorites(tMovie)).thenReturn(null)

        repo.addMovieToFavorites(tMovie)

        verify(mockLocalDataSource, times(1)).addMovieToFavorites(tMovie)
    }

    @Test
    fun removeMovieFromFavorites() = testCoroutineRule.runTest {
        whenever(mockLocalDataSource.removeMovieFromFavorites(tMovie.id)).thenReturn(null)

        repo.removeMovieFromFavorites(tMovie.id)

        verify(mockLocalDataSource, times(1)).removeMovieFromFavorites(tMovie.id)
    }
}