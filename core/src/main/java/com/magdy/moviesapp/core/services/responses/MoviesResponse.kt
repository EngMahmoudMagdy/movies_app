package com.magdy.moviesapp.core.services.responses

import com.google.gson.annotations.SerializedName
import com.magdy.moviesapp.core.models.Movie

data class MoviesResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<Movie>,

    @field:SerializedName("total_results")
    val totalResults: Int
)
