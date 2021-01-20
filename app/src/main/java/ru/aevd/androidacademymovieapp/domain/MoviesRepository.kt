package ru.aevd.androidacademymovieapp.domain

import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}

@Suppress("unused")
class MoviesFromAssets(
    private val moviePersistent: MoviePersistent
    ): MoviesRepository {
    override suspend fun getMovies(): List<Movie> = moviePersistent.loadMovies()
}

class MoviesFromNetwork(
    private val networkLoad: NetworkLoad
    ): MoviesRepository {
    override suspend fun getMovies(): List<Movie> = networkLoad.loadMovies()
}
