package ru.aevd.androidacademymovieapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseResultItem(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("vote_average")
    val ratings: Float,
    @SerialName("vote_count")
    val numberOfRatings: Int,
    @SerialName("adult")
    val adult: Boolean,

    /* Not used params
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double
     */
)

