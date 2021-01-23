package ru.aevd.androidacademymovieapp.domain

import android.content.Context
import ru.aevd.androidacademymovieapp.domain.entities.Actor
import ru.aevd.androidacademymovieapp.domain.entities.Genre
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.storage.AppDatabase
import ru.aevd.androidacademymovieapp.storage.MoviesDao
import ru.aevd.androidacademymovieapp.storage.entities.ActorDb
import ru.aevd.androidacademymovieapp.storage.entities.GenreDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieWithActorsDb

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

    override suspend fun getMoviesFromDb(): List<Movie> =   db.moviesDao.getAllMoviesWithActors()
        .map { movieWithActorsDbToEntity(it) }

}

fun movieWithActorsDbToEntity(mwa: MovieWithActorsDb) = Movie(
    id = mwa.movieDb.id.toInt(),
    title = mwa.movieDb.title,
    overview = mwa.movieDb.overview,
    poster = mwa.movieDb.poster,
    backdrop = mwa.movieDb.backdrop,
    ratings = mwa.movieDb.ratings,
    numberOfRatings = mwa.movieDb.numberOfRatings,
    minimumAge = mwa.movieDb.minimumAge,
    runtime = mwa.movieDb.runtime,
    genres = mwa.movieDb.genres.map { genreDbToEntity(it) },
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
