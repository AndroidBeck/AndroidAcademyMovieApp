package ru.aevd.androidacademymovieapp.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.aevd.androidacademymovieapp.data.Genre

/** Not used params:
 * originalLanguage: String, imdbId: String, video: Boolean, title: String, backdropPath: String,
 * revenue: Int, popularity: Double, voteCount: Int, budget: Int, overview: String,
 * originalTitle: String, posterPath: String, releaseDate: String, voteAverage: Double,
 * adult: Boolean, homepage: String, status: String
 */

@Serializable
data class MovieDetailsResponse (
        @SerialName("id")
        val id: Int,
        @SerialName("runtime")
        val runtime: Int,
        @SerialName("genres")
        val genres: List<Genre>
)
