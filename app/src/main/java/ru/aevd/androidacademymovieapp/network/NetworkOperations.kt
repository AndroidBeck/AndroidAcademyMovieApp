package ru.aevd.androidacademymovieapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.aevd.androidacademymovieapp.BuildConfig
import ru.aevd.androidacademymovieapp.data.Actor
import ru.aevd.androidacademymovieapp.data.Movie
import ru.aevd.androidacademymovieapp.network.response.MovieActorsResponse
import ru.aevd.androidacademymovieapp.network.response.MovieDetailsResponse
import ru.aevd.androidacademymovieapp.network.response.MoviesResponseResultItem

class NetworkOperations {
    private val coroutineContext = Job() + Dispatchers.IO

    //TODO 5: ? add exceptionHandler
    suspend fun loadMovies(): List<Movie> = withContext(coroutineContext) {
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

private class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalRequestUrl = originalRequest.url
        val url = originalRequestUrl.newBuilder()
                .addQueryParameter(BuildConfig.API_KEY_QUERY_PARAM, BuildConfig.API_KEY)
                .build()
        val request = originalRequest.newBuilder().url(url).build()
        return  chain.proceed(request = request)
    }
}

private object RetrofitModule {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //TODO 2: remove logging interceptor in prod
    private val client = OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()


    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    //TODO: you can do it better - watch in the end of the lecture
    val moviesApi: MoviesApi = retrofit.create(MoviesApi::class.java)
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
            genres = movieDetailsResp.genres,
            actors = createActorsFromApiResponse(actorsResp)
    )
}

private fun createActorsFromApiResponse(actorsResp: MovieActorsResponse): List<Actor> {
    val actors: MutableList<Actor> = mutableListOf()
    for(castItem in actorsResp.cast) {
        actors.add(Actor(
                id = castItem.id,
                name = castItem.name,
                picture = getImgPathUrl(profileSizePath, castItem.profilePath)
        ))
    }
    return actors
}

private fun getImgPathUrl(imgSizePath: String, imgPath: String?) =
    "${BuildConfig.BASE_IMG_URL}${imgSizePath}${imgPath}"

/*
"poster_sizes":["w92","w154","w185","w342","w500","w780","original"]
"backdrop_sizes":["w300","w780","w1280","original"]
"profile_sizes":["w45","w185","h632","original"], (w500 also works)
 */
private const val posterSizePath = "w500"
private const val backDropSizePath = "w780"
private const val profileSizePath = "w185"

//private val TAG = NetworkOperations::class.java.simpleName