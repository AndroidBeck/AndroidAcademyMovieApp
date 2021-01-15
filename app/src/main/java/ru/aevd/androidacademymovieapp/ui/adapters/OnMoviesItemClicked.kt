package ru.aevd.androidacademymovieapp.ui.adapters

import ru.aevd.androidacademymovieapp.data.Movie

interface OnMoviesItemClicked {
    fun onClick(movie: Movie)
}