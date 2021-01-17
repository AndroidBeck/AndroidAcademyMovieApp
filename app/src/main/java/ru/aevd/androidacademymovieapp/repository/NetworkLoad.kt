package ru.aevd.androidacademymovieapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import ru.aevd.androidacademymovieapp.BuildConfig
import ru.aevd.androidacademymovieapp.entities.Actor
import ru.aevd.androidacademymovieapp.entities.Genre
import ru.aevd.androidacademymovieapp.entities.Movie
import ru.aevd.androidacademymovieapp.network.RetrofitModule
import ru.aevd.androidacademymovieapp.network.responses.GenreNetModel
import ru.aevd.androidacademymovieapp.network.responses.MovieActorsResponse
import ru.aevd.androidacademymovieapp.network.responses.MovieDetailsResponse
import ru.aevd.androidacademymovieapp.network.responses.MoviesResponseResultItem

class NetworkLoad {
    private val coroutineContext = Job() + Dispatchers.IO

    //TODO 5: ? add exceptionHandler
    suspend fun loadMovies(): List<Movie> = withContext(coroutineContext) {
        //TODO: move page and language to API
        val page  = "1"
        val language = "en-US"
        val options: Map<String, String> = mapOf("page" to page, "language" to language)
        val movies: MutableList<Movie> = mutableListOf()
        val moviesListResponse = RetrofitModule.moviesApi.getMovies(options = options).results
        for (movieResponse in moviesListResponse) {
            val movieDetailsResponse = RetrofitModule.moviesApi.getMovieDetails(movieResponse.id)
            val actorsResponse = RetrofitModule.moviesApi.getMovieActors(movieResponse.id)
            val movie = createMovieFromApiResponses(
                    movieResp = movieResponse,
                    movieDetailsResp = movieDetailsResponse,
                    actorsResp = actorsResponse
            )
            movies.add(movie)
        }
        movies
    }
}

private fun createMovieFromApiResponses(
        movieResp: MoviesResponseResultItem,
        movieDetailsResp: MovieDetailsResponse,
        actorsResp: MovieActorsResponse
): Movie {
    return Movie(
            id = movieResp.id,
            title = movieResp.title,
            overview = movieResp.overview,
            poster = getImgPathUrl(posterSizePath, movieResp.posterPath),
            backdrop = getImgPathUrl(backDropSizePath, movieResp.backdropPath),
            ratings = movieResp.ratings,
            numberOfRatings = movieResp.numberOfRatings,
            minimumAge = if (movieResp.adult) 16 else 13,
            runtime = movieDetailsResp.runtime,
            genres = createGenresFromNetModel(movieDetailsResp.genres),
            actors = createActorsFromApiResponse(actorsResp)
    )
}

private fun createActorsFromApiResponse(actorsResp: MovieActorsResponse): List<Actor> {
    val actors: MutableList<Actor> = mutableListOf()
    for(actorNetModel in actorsResp.cast) {
        actors.add(Actor(
                id = actorNetModel.id,
                name = actorNetModel.name,
                picture = getImgPathUrl(profileSizePath, actorNetModel.profilePath)
        ))
    }
    return actors
}

private fun createGenresFromNetModel(genresNetModel: List<GenreNetModel>): List<Genre> {
    val genres: MutableList<Genre> = mutableListOf()
    for (genreNetModel in genresNetModel) {
        genres.add(Genre(
                id = genreNetModel.id,
                name = genreNetModel.name
        ))
    }
    return genres
}

private fun getImgPathUrl(imgSizePath: String, imgPath: String?) =
        "${BuildConfig.BASE_IMG_URL}${imgSizePath}${imgPath}"

/**
 * "poster_sizes":["w92","w154","w185","w342","w500","w780","original"]
 * "backdrop_sizes":["w300","w780","w1280","original"]
 * "profile_sizes":["w45","w185","h632","original"] (w500 also works)
 */
private const val posterSizePath = "w500"
private const val backDropSizePath = "w780"
private const val profileSizePath = "w185"

//private val TAG = NetworkOperations::class.java.simpleName