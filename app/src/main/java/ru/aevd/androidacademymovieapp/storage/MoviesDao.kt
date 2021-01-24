package ru.aevd.androidacademymovieapp.storage

import androidx.room.*
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieWithGenresAndActorsDb

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies WHERE _id == :id")
    suspend fun getMovieById(id: Long): MovieDb

    @Query("SELECT * FROM movies ORDER BY _id ASC")
    suspend fun getAllMovies(): List<MovieDb>

    @Query("SELECT * FROM movies ORDER BY _id ASC")
    suspend fun getAllMoviesWithGenresAndActors(): List<MovieWithGenresAndActorsDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDb: MovieDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesDb: List<MovieDb>) //: List<Long>

    @Query("DELETE FROM movies WHERE _id == :id")
    suspend fun deleteMovieById(id: Long)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Transaction
    suspend fun clearAndCacheMovies(moviesDb: List<MovieDb>) {
        deleteAllMovies()
        insertMovies(moviesDb)
    }

}