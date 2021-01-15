package ru.aevd.androidacademymovieapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Actor(
    val id: Int,
    val name: String,
    val picture: String
) : Parcelable