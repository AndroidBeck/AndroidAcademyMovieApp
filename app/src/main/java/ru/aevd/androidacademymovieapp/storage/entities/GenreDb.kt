package ru.aevd.androidacademymovieapp.storage.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import ru.aevd.androidacademymovieapp.storage.Contract

@Entity(
    tableName = Contract.Genres.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieDb::class,
        parentColumns = arrayOf(Contract.Genres.COLUMN_NAME_ID),
        childColumns = arrayOf(Contract.Genres.COLUMN_NAME_MOVIE_ID),
        onDelete = CASCADE
    )]
)
data class GenreDb(
    @PrimaryKey
    @ColumnInfo(name = Contract.Genres.COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = Contract.Genres.COLUMN_NAME_NAME)
    val name: String,
    @ColumnInfo(name = Contract.Genres.COLUMN_NAME_MOVIE_ID)
    val movieId: Long
)