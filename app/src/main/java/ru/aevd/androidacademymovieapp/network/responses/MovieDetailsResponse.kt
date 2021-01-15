package ru.aevd.androidacademymovieapp.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.aevd.androidacademymovieapp.data.Genre

@Serializable
data class MovieDetailsResponse (
        @SerialName("id")
        val id: Int,
        @SerialName("runtime")
        val runtime: Int,
        @SerialName("genres")
        val genres: List<Genre>

        /* Not used params
        val originalLanguage: String,
        val imdbId: String,
        val video: Boolean,
        val title: String,
        val backdropPath: String,
        val revenue: Int,
        val popularity: Double,
        val voteCount: Int,
        val budget: Int,
        val overview: String,
        val originalTitle: String,
        val posterPath: String,
        val releaseDate: String,
        val voteAverage: Double,
        val adult: Boolean,
        val homepage: String,
        val status: String
        */
)
