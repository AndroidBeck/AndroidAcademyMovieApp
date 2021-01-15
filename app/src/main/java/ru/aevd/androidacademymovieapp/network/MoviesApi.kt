package ru.aevd.androidacademymovieapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.aevd.androidacademymovieapp.network.response.MovieActorsResponse
import ru.aevd.androidacademymovieapp.network.response.MovieDetailsResponse
import ru.aevd.androidacademymovieapp.network.response.MoviesResponse

/**
 * Movies api of themoviedb.org
 */

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getMovies(
            @QueryMap options: Map<String, String>
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