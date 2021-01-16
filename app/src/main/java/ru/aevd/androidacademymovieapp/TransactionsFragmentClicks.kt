package ru.aevd.androidacademymovieapp

import ru.aevd.androidacademymovieapp.entities.Movie

interface TransactionsFragmentClicks {
    fun showMovieDetails(movie: Movie)
    fun navigateBack()
}