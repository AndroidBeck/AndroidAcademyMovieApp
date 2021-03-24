package ru.aevd.androidacademymovieapp.ui.adapters

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface MoviesItemClickListener {
    fun onClick(movie: Movie)
}