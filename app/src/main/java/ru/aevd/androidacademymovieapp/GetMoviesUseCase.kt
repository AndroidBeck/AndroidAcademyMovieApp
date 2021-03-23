package ru.aevd.androidacademymovieapp

import ru.aevd.androidacademymovieapp.data.Movie

interface GetMoviesUseCase {
    suspend fun getMovies(): List<Movie>
}

class GetMoviesUsingContextUseCase (
        private val moviePersistent: MoviePersistent
        ): GetMoviesUseCase {
    override suspend fun getMovies(): List<Movie> = moviePersistent.loadMovies()
}