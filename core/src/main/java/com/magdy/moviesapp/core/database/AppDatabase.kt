package com.magdy.moviesapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magdy.moviesapp.core.models.Movie

@Database(entities = [(Movie::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesDAO


    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: getInstance(context).also { instance = it }
        }

        private fun getInstance(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movies.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
