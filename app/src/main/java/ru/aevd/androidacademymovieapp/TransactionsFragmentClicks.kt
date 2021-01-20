package ru.aevd.androidacademymovieapp

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface TransactionsFragmentClicks {
    fun showMovieDetails(movie: Movie)
    fun navigateBack()
}