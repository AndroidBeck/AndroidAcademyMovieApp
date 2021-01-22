package ru.aevd.androidacademymovieapp.storage.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import ru.aevd.androidacademymovieapp.storage.MoviesContract

@Entity(
    tableName = MoviesContract.Actors.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieDb::class,
        parentColumns = arrayOf(MoviesContract.Genres.COLUMN_NAME_ID),
        childColumns = arrayOf(MoviesContract.Genres.COLUMN_NAME_MOVIE_ID),
        onDelete = CASCADE
    )]
)
data class ActorDb (
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
