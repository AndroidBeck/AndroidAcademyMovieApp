package ru.aevd.androidacademymovieapp.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.aevd.androidacademymovieapp.domain.entities.Actor
import ru.aevd.androidacademymovieapp.domain.entities.Genre
import ru.aevd.androidacademymovieapp.storage.MoviesContract

//TODO 1: indexes: add genres, actors ? or use foreign keys
@Entity(
    tableName = MoviesContract.Movies.TABLE_NAME,
    indices = [Index(MoviesContract.Movies.COLUMN_NAME_ID)]
)
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_ID)
    val id: Long = 0,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_TITLE)
    val title: String,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_POSTER)
    val poster: String,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_BACKDROP)
    val backdrop: String,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_RATINGS)
    val ratings: Float,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_RATINGS_NUMBER)
    val numberOfRatings: Int,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_MIN_AGE)
    val minimumAge: Int,
    @ColumnInfo(name = MoviesContract.Movies.COLUMN_NAME_RUNTIME)
    val runtime: Int
)
