package ru.aevd.androidacademymovieapp.domain

import android.content.Context
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.storage.AppDatabase
import ru.aevd.androidacademymovieapp.storage.MoviesDao

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}

class DefaultMoviesRepository(private val appContext: Context): MoviesRepository {
    private val networkLoad: NetworkLoad = NetworkLoad()
    private val db = AppDatabase.createDb(appContext)

    @Suppress("unused")
    //Use JsonLoad
    suspend fun getMoviesFromAssets(): List<Movie> = loadMovies(appContext)

    suspend fun getMoviesFromNet(): List<Movie> = networkLoad.loadMovies()

    //suspend fun getMoviesFromDb(): List<Movie> =   db.moviesDao.getAllMovies().map { to }

    override suspend fun getMovies(): List<Movie> = getMoviesFromNet()

}

