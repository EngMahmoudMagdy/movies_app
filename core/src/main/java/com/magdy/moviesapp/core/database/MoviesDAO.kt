package com.magdy.moviesapp.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.magdy.moviesapp.core.models.Movie

@Dao
interface MoviesDAO {
    @Query("SELECT * FROM movies")
    fun getMovieList(): List<Movie>

    @Insert
    fun insertMovie(movie: Movie)


    @Query("Delete FROM movies where id = :movieId")
    fun deleteMovie(movieId: Int)
}