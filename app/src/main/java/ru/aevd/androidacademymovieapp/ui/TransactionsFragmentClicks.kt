package ru.aevd.androidacademymovieapp.ui

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface TransactionsFragmentClicks {
    fun showMovieDetails(movie: Movie)
    fun navigateBack()
}