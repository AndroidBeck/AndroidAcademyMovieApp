package ru.aevd.androidacademymovieapp.ui.adapters

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface OnMoviesItemClicked {
    fun onClick(movie: Movie)
}