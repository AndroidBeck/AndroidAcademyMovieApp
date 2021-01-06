package ru.aevd.androidacademymovieapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(LoadMovieInteractor())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}