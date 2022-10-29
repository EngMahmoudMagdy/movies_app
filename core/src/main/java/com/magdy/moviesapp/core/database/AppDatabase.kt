package com.magdy.moviesapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magdy.moviesapp.core.models.Movie

@Database(entities = [(Movie::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesDAO

}
