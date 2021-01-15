package ru.aevd.androidacademymovieapp.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieActorsResponse (
    @SerialName("cast")
    val cast: List<CastItem>
)

@Serializable
data class CastItem(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profile_path")
        val profilePath: String?

        /* Not used params
        val castId: Int,
        val character: String,
        val gender: Int,
        val creditId: String,
        val knownForDepartment: String,
        val originalName: String,
        val popularity: Double,
        val adult: Boolean,
        val order: Int
         */
)
