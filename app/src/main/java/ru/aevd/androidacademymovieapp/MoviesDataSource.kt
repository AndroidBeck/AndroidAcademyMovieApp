package ru.aevd.androidacademymovieapp

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
                Movie(
                        name = "Avengers: End Game",
                        description = "After the devastating events of Avengers: Infinity War, " +
                                "the universe is in ruins. With the help of remaining allies, " +
                                "the Avengers assemble once more in order to reverse Thanos' " +
                                "actions and restore balance to the universe.",
                        genres = "Action, Adventure, Drama",
                        rateInStars = 4,
                        reviewsNumber = 125,
                        ageRate = 13,
                        timeDurationInMinutes = 137,
                        isLiked = false,
                        cast = listOf(
                                Actor(
                                        name = "Robert Downey Jr.",
                                        img_path = R.drawable.actor_avatar_robert_downey_jr
                                ),
                                Actor(
                                        name = "Chris Evans",
                                        img_path = R.drawable.actor_avatar_chris_evans
                                ),
                                Actor(
                                        name = "Mark Ruffalo",
                                        img_path = R.drawable.actor_avatar_mark_ruffalo
                                ),
                                Actor(
                                        name = "Chris Hemsworth",
                                        img_path = R.drawable.actor_avatar_chris_hemsworth
                                )
                        ),
                        logo_path = R.drawable.movie_avengers_end_game_logo,
                        logo_small_path = R.drawable.movie_avengers_end_game_logo_small
                ),

                Movie(
                        name = "Tenet",
                        description = "---",
                        genres = "Action, Sci-Fi, Thriller",
                        rateInStars = 5,
                        reviewsNumber = 98,
                        ageRate = 16,
                        timeDurationInMinutes = 97,
                        isLiked = true,
                        cast = listOf(
                                Actor(
                                        name = "---",
                                        img_path = R.drawable.actor_avatar_robert_downey_jr
                                )
                        ),
                        logo_path = R.drawable.movie_avengers_end_game_logo,
                        logo_small_path = R.drawable.movie_tenet_logo_small
                ),

                Movie(
                        name = "Black Widow",
                        description = "---",
                        genres = "Action, Adventure, Sci-Fi",
                        rateInStars = 4,
                        reviewsNumber = 38,
                        ageRate = 13,
                        timeDurationInMinutes = 102,
                        isLiked = false,
                        cast = listOf(
                                Actor(
                                        name = "---",
                                        img_path = R.drawable.actor_avatar_robert_downey_jr
                                )
                        ),
                        logo_path = R.drawable.movie_avengers_end_game_logo,
                        logo_small_path = R.drawable.movie_black_widow_logo_small
                ),

                Movie(
                        name = "Wonder Woman 1984",
                        description = "---",
                        genres = "Action, Adventure, Fantasy",
                        rateInStars = 5,
                        reviewsNumber = 74,
                        ageRate = 13,
                        timeDurationInMinutes = 120,
                        isLiked = false,
                        cast = listOf(
                                Actor(
                                        name = "---",
                                        img_path = R.drawable.actor_avatar_robert_downey_jr
                                )
                        ),
                        logo_path = R.drawable.movie_avengers_end_game_logo,
                        logo_small_path = R.drawable.movie_wonder_woman_1984_logo_small
                )
        )
    }

}
