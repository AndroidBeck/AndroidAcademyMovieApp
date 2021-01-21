package ru.aevd.androidacademymovieapp.storage.entities

import androidx.room.*
import ru.aevd.androidacademymovieapp.storage.MoviesContract

@Entity(
    tableName = MoviesContract.Actors.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf(MoviesContract.Genres.COLUMN_NAME_ID),
        childColumns = arrayOf(MoviesContract.Genres.COLUMN_NAME_MOVIE_ID),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ActorEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MoviesContract.Actors.COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = MoviesContract.Actors.COLUMN_NAME_NAME)
    val name: String,
    @ColumnInfo(name = MoviesContract.Actors.COLUMN_NAME_PICTURE)
    val picture: String,
    @ColumnInfo(name = MoviesContract.Actors.COLUMN_NAME_MOVIE_ID)
    val movieId: Long
)
