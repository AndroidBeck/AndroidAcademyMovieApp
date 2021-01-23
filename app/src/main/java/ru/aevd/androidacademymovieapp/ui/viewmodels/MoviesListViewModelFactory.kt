package ru.aevd.androidacademymovieapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.aevd.androidacademymovieapp.App
import ru.aevd.androidacademymovieapp.domain.MoviesRepository

class MoviesListViewModelFactory(
        private val appContext: Context
        ): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MoviesListViewModel::class.java -> {
            val repository = (appContext as App).moviesRepository
            MoviesListViewModel(repository)
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}