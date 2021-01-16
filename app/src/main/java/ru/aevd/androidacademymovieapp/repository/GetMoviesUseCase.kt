package ru.aevd.androidacademymovieapp.repository

import ru.aevd.androidacademymovieapp.entities.Movie

interface GetMoviesUseCase {
    suspend fun getMovies(): List<Movie>
}

class GetMoviesFromAssets(
    private val moviePersistent: MoviePersistent
    ): GetMoviesUseCase {
    override suspend fun getMovies(): List<Movie> = moviePersistent.loadMovies()
}

class GetMoviesFromNetwork(
    private val networkLoad: NetworkLoad
    ): GetMoviesUseCase {
    override suspend fun getMovies(): List<Movie> = networkLoad.loadMovies()
}
