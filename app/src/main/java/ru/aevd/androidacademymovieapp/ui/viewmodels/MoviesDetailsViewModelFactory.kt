package ru.aevd.androidacademymovieapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.aevd.androidacademymovieapp.domain.entities.Movie

class MoviesDetailsViewModelFactory(
        private val movie: Movie
        ): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}