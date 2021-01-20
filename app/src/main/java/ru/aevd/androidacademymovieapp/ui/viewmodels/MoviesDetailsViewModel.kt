package ru.aevd.androidacademymovieapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aevd.androidacademymovieapp.domain.entities.Movie

class MoviesDetailsViewModel(movie: Movie): ViewModel() {
    private val _movie = MutableLiveData(movie)
    val movie get() = _movie
}