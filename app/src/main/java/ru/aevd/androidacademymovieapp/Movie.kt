package ru.aevd.androidacademymovieapp

data class Movie (
    val name: String,
    val description: String,
    val rateInStars: Int,
    val reviewsNumber: Int,
    val ageRate: Int,
    val timeDurationInMinutes: Int,
    val isLiked: Boolean,
    val cast: List<Actor>,
    val logo_path: String,
    val logo_small_path: String
)