package ru.aevd.androidacademymovieapp.domain

import android.content.Context
import android.util.Log
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import ru.aevd.androidacademymovieapp.R
import ru.aevd.androidacademymovieapp.domain.entities.Actor
import ru.aevd.androidacademymovieapp.domain.entities.Genre
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.storage.AppDatabase
import ru.aevd.androidacademymovieapp.storage.entities.*

interface MoviesRepository {
    suspend fun getMoviesFromAssets(): List<Movie>
    suspend fun getMoviesResultFromNet(): Result<List<Movie>>
    fun getMoviesFlowFromDb(): Flow<List<Movie>>
    suspend fun saveMoviesToDb(movies: List<Movie>)
}

class DefaultMoviesRepository(private val appContext: Context): MoviesRepository {
    private val networkLoad: NetworkLoad = NetworkLoad()
    private val db = AppDatabase.createDb(appContext)

    @Suppress("unused")
    //Use JsonLoad
    override suspend fun getMoviesFromAssets(): List<Movie> = loadMovies(appContext)

    override suspend fun getMoviesResultFromNet(): Result<List<Movie>> = withContext(Dispatchers.IO) {
        try {
            val movies = networkLoad.loadMovies()
            Result.Success(movies)
        } catch (e: Exception) {
            val message = setErrorMessage(e)
            Result.Error(message)
        }
    }

    override fun getMoviesFlowFromDb() = db.moviesDao.getAllMoviesWithGenresAndActorsFlow()
            .map { movies -> movies.map {movie -> movieDbToEntity(movie) } }

    override suspend fun saveMoviesToDb(movies: List<Movie>) = withContext(Dispatchers.IO) {
        val mwaDb = movies.map { movieEntityToDb(it) }
        val moviesDb: List<MovieDb> = mwaDb.map { it.mDb }
        val genresListDb: List<GenreDb> = (mwaDb.map { it.genres }).flatten()
        val actorsListDb: List<ActorDb> = (mwaDb.map { it.actors }).flatten()
        db.moviesDao.insertMoviesWithGenresAndActors(
            moviesDb,
            genresListDb,
            actorsListDb
        )
    }

    private fun setErrorMessage(e: Exception): String {
        Log.e(TAG, "Handling exception: $e", e)
        return when (e) {
            is HttpException -> appContext.getString(R.string.load_error_http)
            is java.io.IOException -> appContext.getString(R.string.load_error_io)
            is SerializationException -> appContext.getString(R.string.load_error_serialization)
            else -> appContext.getString(R.string.load_error_unknown)
        }
    }

}

fun movieDbToEntity(mwa: MovieWithGenresAndActorsDb) = Movie(
    id = mwa.mDb.id.toInt(),
    title = mwa.mDb.title,
    overview = mwa.mDb.overview,
    poster = mwa.mDb.poster,
    backdrop = mwa.mDb.backdrop,
    ratings = mwa.mDb.ratings,
    numberOfRatings = mwa.mDb.numberOfRatings,
    minimumAge = mwa.mDb.minimumAge,
    runtime = mwa.mDb.runtime,
    genres = mwa.genres.map { genreDbToEntity(it) },
    actors = mwa.actors.map { actorDbToEntity(it) }
)

fun genreDbToEntity(genreDb: GenreDb) = Genre(
    id = genreDb.id,
    name = genreDb.name
)

fun actorDbToEntity(actorDb: ActorDb) = Actor(
    id = actorDb.id,
    name = actorDb.name,
    picture = actorDb.picture
)

fun movieEntityToDb(movie: Movie) = MovieWithGenresAndActorsDb(
    mDb = MovieDb(
        id = movie.id.toLong(),
        title = movie.title,
        overview = movie.overview,
        poster = movie.poster,
        backdrop = movie.backdrop,
        ratings = movie.ratings,
        numberOfRatings = movie.numberOfRatings,
        minimumAge = movie.minimumAge,
        runtime = movie.runtime
    ),
    genres = movie.genres.map { genreEntityToDb(it, movie.id) },
    actors = movie.actors.map { actorEntityToDb(it, movie.id) }
)

fun genreEntityToDb(genre: Genre, movieId: Int) = GenreDb(
    id = genre.id,
    name = genre.name,
    movieId = movieId.toLong()
)

fun actorEntityToDb(actor: Actor, movieId: Int) = ActorDb(
    id = actor.id,
    name = actor.name,
    picture = actor.picture,
    movieId = movieId.toLong()
)

private val TAG = DefaultMoviesRepository::class.java.simpleName