package ru.aevd.androidacademymovieapp.network

import retrofit2.http.GET
import ru.aevd.androidacademymovieapp.data.Movie

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getMovies(): List<Movie>
}