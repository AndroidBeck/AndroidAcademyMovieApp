package ru.aevd.androidacademymovieapp.domain

import kotlinx.coroutines.flow.Flow
import ru.aevd.androidacademymovieapp.domain.entities.Movie

interface MoviesRepository {
    suspend fun getMoviesFromAssets(): List<Movie>
    suspend fun getMoviesResultFromNet(): Result<List<Movie>>
    fun getMoviesFlowFromDb(): Flow<List<Movie>>
    suspend fun saveMoviesToDb(movies: List<Movie>)
}