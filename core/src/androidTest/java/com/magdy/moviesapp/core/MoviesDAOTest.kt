package com.magdy.moviesapp.core

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.magdy.moviesapp.core.database.AppDatabase
import com.magdy.moviesapp.core.database.MoviesDAO
import com.magdy.moviesapp.core.models.Movie
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDAOTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var moviesDAO: MoviesDAO
    private val tMovie = Movie(
        "", "", "", false, "", "", "", "", 0.0, 0.0, 0, false, 0, null
    )

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        try {
            database = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java
            ).allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.e("test", e.message ?: "")
        }
        moviesDAO = database.moviesDAO()
    }

    @Test
    fun testAddingMovie() = runTest {
        val preInsertMovies = moviesDAO.getMovieList(20, 0)

        moviesDAO.insertMovie(tMovie)
        val postInsertMovies = moviesDAO.getMovieList(20, 0)
        val sizeDifference = postInsertMovies.size - preInsertMovies.size
        Assert.assertEquals(1, sizeDifference)
        val retrievedMovie = postInsertMovies.last()
        Assert.assertEquals(0, retrievedMovie.id)
    }

    @Test
    fun testDeletingMovie() = runTest {
        val preInsertMovies = moviesDAO.getMovieList(20, 0)
        moviesDAO.insertMovie(tMovie)
        moviesDAO.deleteMovie(tMovie.id)
        val postInsertMovies = moviesDAO.getMovieList(20, 0)
        val sizeDifference = postInsertMovies.size - preInsertMovies.size
        Assert.assertEquals(0, sizeDifference)
    }


    @After
    fun tearDown() {
        database.close()
    }
}