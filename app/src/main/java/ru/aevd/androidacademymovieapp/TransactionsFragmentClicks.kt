package ru.aevd.androidacademymovieapp

import ru.aevd.androidacademymovieapp.data.Movie

interface TransactionsFragmentClicks {
    fun showMovieDetails(movie: Movie)
    fun navigateBack()
}