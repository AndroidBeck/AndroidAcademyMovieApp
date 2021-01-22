package ru.aevd.androidacademymovieapp.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.aevd.androidacademymovieapp.App
import ru.aevd.androidacademymovieapp.storage.entities.ActorDb
import ru.aevd.androidacademymovieapp.storage.entities.GenreDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb

@Database(entities = [MovieDb::class, ActorDb::class, GenreDb::class], version = 1)
//@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao
    //abstract val actorsDao: ActorsDao

    companion object {

        fun createDb(appContext: Context) = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Contract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}