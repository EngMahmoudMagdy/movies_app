package com.magdy.moviesapp.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.domain.repos.MoviesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieFromFavoriteUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var moviesRepo: MoviesRepo

    lateinit var getMovieFromFavoritesUseCase: GetMovieFromFavoritesUseCase

    private val tMovie = Movie(
        "", "", "", false, "", "", "", "", 0.0, 0.0, 0, false, 0, null
    )
    private val tPage = 1

    @Before
    fun setUp() {
        getMovieFromFavoritesUseCase = GetMovieFromFavoritesUseCase(moviesRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetNowPlayingMoviesList() = testCoroutineRule.runTest {
        whenever(moviesRepo.getMovieFromFavoriteById(tMovie.id)).thenAnswer {
            tMovie
        }
        val result = getMovieFromFavoritesUseCase(tMovie.id)

        assertEquals(result?.id, tMovie.id)

    }
}