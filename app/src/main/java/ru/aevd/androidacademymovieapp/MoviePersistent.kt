package ru.aevd.androidacademymovieapp

import android.content.Context
import ru.aevd.androidacademymovieapp.data.Movie
import ru.aevd.androidacademymovieapp.data.loadMovies

class MoviePersistent(private val context: Context) {
    suspend fun loadMovies(): List<Movie> = loadMovies(context)
}