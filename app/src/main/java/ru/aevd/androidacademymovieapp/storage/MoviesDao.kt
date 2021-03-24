package ru.aevd.androidacademymovieapp.storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.aevd.androidacademymovieapp.storage.entities.ActorDb
import ru.aevd.androidacademymovieapp.storage.entities.GenreDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieWithGenresAndActorsDb

@Dao
interface MoviesDao {

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

}