package ru.aevd.androidacademymovieapp.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieActorsResponse (
    @SerialName("cast")
    val cast: List<ActorNetModel>
)

/** Not used params:
 * castId: Int, character: String, gender: Int, creditId: String, knownForDepartment: String,
 * originalName: String, popularity: Double, adult: Boolean, order: Int
 */

@Serializable
data class ActorNetModel(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profile_path")
        val profilePath: String?
)
