package ru.aevd.androidacademymovieapp.storage

import android.provider.BaseColumns

object MoviesContract {

    const val DATABASE_NAME = "MoviesDb.db"

    object Movies {
        const val TABLE_NAME = "movies"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_POSTER = "poster"
        const val COLUMN_NAME_BACKDROP = "backdrop"
        const val COLUMN_NAME_RATINGS = "ratings"
        const val COLUMN_NAME_RATINGS_NUMBER = "numberOfRatings"
        const val COLUMN_NAME_MIN_AGE = "minimumAge"
        const val COLUMN_NAME_RUNTIME = "runtime"
    }

    object Genres {
        const val TABLE_NAME = "genres"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_MOVIE_ID = "movieId"
    }

    object Actors {
        const val TABLE_NAME = "actors"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_MOVIE_ID = "movieId"
    }

}