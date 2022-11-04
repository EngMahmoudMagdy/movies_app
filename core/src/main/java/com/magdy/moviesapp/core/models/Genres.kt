package com.magdy.moviesapp.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    val id: String,
    val name: String
) : Parcelable
