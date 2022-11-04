package com.magdy.moviesapp.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magdy.moviesapp.core.models.Genres

class TypeDatabaseConverter {

    @TypeConverter
    fun fromStore(genres: List<Genres>?): String? {
        if (genres == null) return null
        val type = object : TypeToken<List<Genres>>() {}.type
        return Gson().toJson(genres, type)
    }

    @TypeConverter
    fun toStore(genresString: String?): List<Genres>? {
        if (genresString == null) return null
        val type = object : TypeToken<List<Genres>>() {}.type
        return Gson().fromJson(genresString, type)
    }
}