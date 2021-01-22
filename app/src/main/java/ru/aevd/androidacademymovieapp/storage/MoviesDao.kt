package ru.aevd.androidacademymovieapp.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies WHERE _id == :id")
    suspend fun getMovie(id: Long): MovieDb

    @Query("SELECT * FROM movies ORDER BY _id ASC")
    suspend fun getAllMovies(): List<MovieDb>

    @Insert
    suspend fun insertMovie(movieDb: MovieDb)

    @Insert
    suspend fun insertMovies(moviesDb: List<MovieDb>) //: List<Long>

    @Query("DELETE FROM movies WHERE _id == :id")
    suspend fun deleteMovie(id: Long)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Transaction
    suspend fun clearAndCacheMovies(moviesDb: List<MovieDb>) {
        deleteAllMovies()
        insertMovies(moviesDb)
    }

}