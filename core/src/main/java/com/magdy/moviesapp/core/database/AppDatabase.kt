package com.magdy.moviesapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magdy.moviesapp.core.models.Movie

@Database(entities = [(Movie::class)], version = 2)
@TypeConverters(TypeDatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesDAO

}
