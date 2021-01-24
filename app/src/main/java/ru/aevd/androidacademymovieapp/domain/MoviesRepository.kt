package ru.aevd.androidacademymovieapp.domain

import android.content.Context
import ru.aevd.androidacademymovieapp.domain.entities.Actor
import ru.aevd.androidacademymovieapp.domain.entities.Genre
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.storage.AppDatabase
import ru.aevd.androidacademymovieapp.storage.MoviesDao
import ru.aevd.androidacademymovieapp.storage.entities.*

interface MoviesRepository {
    suspend fun getMoviesFromAssets(): List<Movie>
    suspend fun getMoviesFromNet(): List<Movie>
    suspend fun getMoviesFromDb(): List<Movie>
}

class DefaultMoviesRepository(private val appContext: Context): MoviesRepository {
    private val networkLoad: NetworkLoad = NetworkLoad()
    private val db = AppDatabase.createDb(appContext)

    @Suppress("unused")
    //Use JsonLoad
    override suspend fun getMoviesFromAssets(): List<Movie> = loadMovies(appContext)

    override suspend fun getMoviesFromNet(): List<Movie> = networkLoad.loadMovies()

    override suspend fun getMoviesFromDb(): List<Movie> =   db.moviesDao
        .getAllMoviesWithGenresAndActors()
        .map { movieWithActorsDbToEntity(it) }

}

fun movieWithActorsDbToEntity(mwa: MovieWithGenresAndActorsDb) = Movie(
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
