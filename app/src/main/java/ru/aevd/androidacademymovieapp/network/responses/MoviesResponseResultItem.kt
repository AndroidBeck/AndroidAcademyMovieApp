package ru.aevd.androidacademymovieapp.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Not used params:
 * genreIds: List<Int>, originalLanguage: String, originalTitle: String, video: Boolean,
 * releaseDate: String, popularity: Double, voteAverage: Double
 */

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
)

