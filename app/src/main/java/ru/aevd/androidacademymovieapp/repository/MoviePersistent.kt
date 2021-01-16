package ru.aevd.androidacademymovieapp.repository

import android.content.Context
import ru.aevd.androidacademymovieapp.entities.Movie

class MoviePersistent(private val context: Context) {
    suspend fun loadMovies(): List<Movie> = loadMovies(context)
}