package ru.aevd.androidacademymovieapp

data class Movie (
    val id: Long,
    val name: String,
    val description: String,
    val genres: String,
    val rateInStars: Int,
    val reviewsNumber: Int,
    val ageRate: Int,
    val timeDurationInMinutes: Int,
    var isLiked: Boolean,
    val cast: List<Actor>,
    val logo_path: Int,
    val logo_small_path: Int
)