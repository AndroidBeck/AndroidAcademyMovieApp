package ru.aevd.androidacademymovieapp.ui

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface FragmentClickListener {
    fun onShowDetailsClick(movie: Movie)
    fun onBackClick()
}