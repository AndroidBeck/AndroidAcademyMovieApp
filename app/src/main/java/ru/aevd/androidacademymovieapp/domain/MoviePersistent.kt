package ru.aevd.androidacademymovieapp.domain

import android.content.Context
import ru.aevd.androidacademymovieapp.domain.entities.Movie

class MoviePersistent(private val context: Context) {
    suspend fun loadMovies(): List<Movie> = loadMovies(context)
}