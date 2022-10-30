package com.magdy.moviesapp.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.magdy.moviesapp.core.models.Movie

@Dao
interface MoviesDAO {
    @Query("SELECT * FROM movies LIMIT :limit OFFSET :offset")
    suspend fun getMovieList(limit: Int, offset: Int): List<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)


    @Query("Delete FROM movies where id = :movieId")
    suspend fun deleteMovie(movieId: Int)
}