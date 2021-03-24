package ru.aevd.androidacademymovieapp.storage.entities

import androidx.room.*
import ru.aevd.androidacademymovieapp.storage.Contract

@Entity(
    tableName = Contract.Movies.TABLE_NAME,
    indices = [Index(Contract.Movies.COLUMN_NAME_ID)]
)
data class MovieDb (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_ID)
    val id: Long = 0,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_TITLE)
    val title: String,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_POSTER)
    val poster: String,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_BACKDROP)
    val backdrop: String,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_RATINGS)
    val ratings: Float,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_RATINGS_NUMBER)
    val numberOfRatings: Int,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_MIN_AGE)
    val minimumAge: Int,
    @ColumnInfo(name = Contract.Movies.COLUMN_NAME_RUNTIME)
    val runtime: Int,
)

data class MovieWithGenresAndActorsDb (
    @Embedded
    val mDb: MovieDb,
    @Relation(
            parentColumn = Contract.Movies.COLUMN_NAME_ID,
            entityColumn = Contract.Genres.COLUMN_NAME_MOVIE_ID)
    val genres: List<GenreDb>,
    @Relation(
        parentColumn = Contract.Movies.COLUMN_NAME_ID,
        entityColumn = Contract.Actors.COLUMN_NAME_MOVIE_ID)
    val actors: List<ActorDb>
)