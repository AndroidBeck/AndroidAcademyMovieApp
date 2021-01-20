package ru.aevd.androidacademymovieapp.api.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
        val genres: List<GenreNetModel>
)

@Serializable
data class GenreNetModel(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
)