package ru.aevd.androidacademymovieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aevd.androidacademymovieapp.data.Movie

class MoviesDetailsViewModel(movie: Movie): ViewModel() {
    private val _movie = MutableLiveData(movie)
    val movie get() = _movie
}