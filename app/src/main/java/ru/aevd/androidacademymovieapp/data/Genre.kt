package ru.aevd.androidacademymovieapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize @Serializable
data class Genre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
) : Parcelable