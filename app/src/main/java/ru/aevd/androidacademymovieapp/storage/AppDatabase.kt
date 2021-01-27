package ru.aevd.androidacademymovieapp.storage

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.aevd.androidacademymovieapp.storage.entities.ActorDb
import ru.aevd.androidacademymovieapp.storage.entities.GenreDb
import ru.aevd.androidacademymovieapp.storage.entities.MovieDb

@Database(entities = [MovieDb::class, ActorDb::class, GenreDb::class], version = 1)
//@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao
    //abstract val actorsDao: ActorsDao

    companion object {

        fun createDb(appContext: Context): AppDatabase {
            Log.d("AppDatabase", "Start creating db")
            val db = Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    Contract.DATABASE_NAME
            )
                    .fallbackToDestructiveMigration()
                    .build()
            Log.d("AppDatabase", "Db created")
            return db
        }
    }

}