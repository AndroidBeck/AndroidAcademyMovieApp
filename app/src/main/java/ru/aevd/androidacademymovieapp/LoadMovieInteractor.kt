package ru.aevd.androidacademymovieapp

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.aevd.androidacademymovieapp.data.loadMovies
import ru.aevd.androidacademymovieapp.data.Movie

class LoadMovieInteractor(private val context: Context) {
    suspend fun loadMovie(): List<Movie> {
        loadMovies(context = context)
    }
}