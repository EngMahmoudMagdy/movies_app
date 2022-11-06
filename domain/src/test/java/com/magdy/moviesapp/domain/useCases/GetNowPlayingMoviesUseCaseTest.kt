package com.magdy.moviesapp.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.domain.repos.MoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class GetNowPlayingMoviesUseCaseTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var moviesRepo: MoviesRepo

    lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase


    private val tMovie = Movie(
        "", "", "", false, "", "", "", "", 0.0, 0.0, 0, false, 0, null
    )
    private val tPage = 1

    @Before
    fun setUp() {
        getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(moviesRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetNowPlayingMoviesList() = testCoroutineRule.runTest{
        whenever(moviesRepo.getNowPlayingMovies(0)).thenAnswer{
            flowOf(NetworkResult.Success(
                MoviesResponse(
                    tPage,
                    1,
                    listOf(tMovie), 1
                )
            ))
        }
        val flow = getNowPlayingMoviesUseCase(0).first()

        Assert.assertEquals(flow.data?.results?.get(0)?.id, tMovie.id)

    }
}