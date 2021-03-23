package ru.aevd.androidacademymovieapp

import ru.aevd.androidacademymovieapp.data.Movie

interface OnMoviesItemClicked {
    fun onClick(movie: Movie)
}