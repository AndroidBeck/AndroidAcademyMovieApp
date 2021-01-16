package ru.aevd.androidacademymovieapp.ui.adapters

import ru.aevd.androidacademymovieapp.entities.Movie

interface OnMoviesItemClicked {
    fun onClick(movie: Movie)
}