package ru.aevd.androidacademymovieapp.repository

import ru.aevd.androidacademymovieapp.data.Movie
import ru.aevd.androidacademymovieapp.network.NetworkOperations

interface GetMoviesUseCase {
    suspend fun getMovies(): List<Movie>
}

class GetMoviesFromAssets(
    private val moviePersistent: MoviePersistent
    ): GetMoviesUseCase {
    override suspend fun getMovies(): List<Movie> = moviePersistent.loadMovies()
}

class GetMoviesFromNetwork(
    private val networkOperations: NetworkOperations
    ): GetMoviesUseCase {
    override suspend fun getMovies(): List<Movie> = networkOperations.loadMovies()
}
