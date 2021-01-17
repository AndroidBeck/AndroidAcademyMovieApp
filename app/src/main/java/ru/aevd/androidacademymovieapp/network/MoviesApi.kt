package ru.aevd.androidacademymovieapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.aevd.androidacademymovieapp.network.responses.MovieActorsResponse
import ru.aevd.androidacademymovieapp.network.responses.MovieDetailsResponse
import ru.aevd.androidacademymovieapp.network.responses.MoviesResponse

/**
 * Movies api of themoviedb.org
 */

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getMovies(
            @Query("page") page: Int  = 1,
            @Query("language") language: String = "en-US"
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
            @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(
            @Path("movie_id") movieId: Int
    ): MovieActorsResponse

}