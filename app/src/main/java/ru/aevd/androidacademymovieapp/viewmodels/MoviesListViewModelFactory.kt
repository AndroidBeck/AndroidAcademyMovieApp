package ru.aevd.androidacademymovieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.aevd.androidacademymovieapp.repository.GetMoviesUseCase

class MoviesListViewModelFactory(
        private val getMoviesUseCase: GetMoviesUseCase
        ): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(getMoviesUseCase)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}