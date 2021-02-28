package ru.aevd.androidacademymovieapp.storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.aevd.androidacademymovieapp.storage.entities.ActorDb
import ru.aevd.androidacademymovieapp.storage.entities.GenreDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieWithGenresAndActorsDb

@Dao
interface MoviesDao {

    //ORDER BY _id ASC
    @Transaction @Query("SELECT * FROM movies")
    fun getAllMoviesWithGenresAndActorsFlow(): Flow<List<MovieWithGenresAndActorsDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesWithGenresAndActors(
        moviesDb: List<MovieDb>,
        genresDb: List<GenreDb>,
        actorsDb: List<ActorDb>
    ) //: List<Long>

    @Transaction @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

//    @Query("SELECT * FROM movies WHERE _id == :id")
//    suspend fun getMovieById(id: Long): MovieDb
//
//    @Query("SELECT * FROM movies ORDER BY _id ASC")
//    suspend fun getAllMovies(): List<MovieDb>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovie(movieDb: MovieDb)

//    @Query("DELETE FROM movies WHERE _id == :id")
//    suspend fun deleteMovieById(id: Long)


//    @Transaction
//    suspend fun clearAndCacheMovies(moviesDb: List<MovieWithGenresAndActorsDb>) {
//        deleteAllMovies()
//        insertMovies(moviesDb)
//    }

}