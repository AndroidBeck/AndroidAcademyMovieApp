package ru.aevd.androidacademymovieapp

class MoviesDataSource {

    val movies = listOf(
            Movie(
                    id = 1,
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
                                    id = 1,
                                    name = "Robert Downey Jr.",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 2,
                                    name = "Chris Evans",
                                    img_path = R.drawable.actor_avatar_chris_evans
                            ),
                            Actor(
                                    id = 3,
                                    name = "Mark Ruffalo",
                                    img_path = R.drawable.actor_avatar_mark_ruffalo
                            ),
                            Actor(
                                    id = 4,
                                    name = "Chris Hemsworth",
                                    img_path = R.drawable.actor_avatar_chris_hemsworth
                            )
                    ),
                    logo_path = R.drawable.movie_avengers_end_game_logo,
                    logo_small_path = R.drawable.movie_avengers_end_game_logo_small
            ),

            Movie(
                    id = 2,
                    name = "Tenet",
                    description = "Armed with only one word, Tenet, and fighting for the " +
                            "survival of the entire world, a Protagonist journeys through a " +
                            "twilight world of international espionage on a mission that will " +
                            "unfold in something beyond real time.",
                    genres = "Action, Sci-Fi, Thriller",
                    rateInStars = 5,
                    reviewsNumber = 98,
                    ageRate = 16,
                    timeDurationInMinutes = 97,
                    isLiked = true,
                    cast = listOf(
                            Actor(
                                    id = 1,
                                    name = "John David Washington",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 2,
                                    name = "Robert Pattinson",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 3,
                                    name = "Elizabeth Debicki",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            )
                    ),
                    logo_path = R.drawable.movie_avengers_end_game_logo,
                    logo_small_path = R.drawable.movie_tenet_logo_small
            ),

            Movie(
                    id = 3,
                    name = "Black Widow",
                    description = "A film about Natasha Romanoff in her quests between the " +
                            "films Civil War and Infinity War.",
                    genres = "Action, Adventure, Sci-Fi",
                    rateInStars = 4,
                    reviewsNumber = 38,
                    ageRate = 13,
                    timeDurationInMinutes = 102,
                    isLiked = false,
                    cast = listOf(
                            Actor(
                                    id = 1,
                                    name = "Scarlett Johansson",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 2,
                                    name = "Florence Pugh",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 3,
                                    name = "Rachel Weisz",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 4,
                                    name = "David Harbour",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 5,
                                    name = "Ray Winstone",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            )
                    ),
                    logo_path = R.drawable.movie_avengers_end_game_logo,
                    logo_small_path = R.drawable.movie_black_widow_logo_small
            ),

            Movie(
                    id = 4,
                    name = "Wonder Woman 1984",
                    description = "Continuation of the fantastic exploits of the Amazon warrior " +
                            "Diana, who left her secluded island to save humanity. After " +
                            "overcoming the tragedy of World War I, Wonder Woman is embroiled " +
                            "in a new international battle of the mid-1980s.",
                    genres = "Action, Adventure, Fantasy",
                    rateInStars = 5,
                    reviewsNumber = 74,
                    ageRate = 13,
                    timeDurationInMinutes = 120,
                    isLiked = false,
                    cast = listOf(
                            Actor(
                                    id = 1,
                                    name = "Gal Gadot",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 1,
                                    name = "Chris Pine",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            ),
                            Actor(
                                    id = 1,
                                    name = "Kristen Wiig",
                                    img_path = R.drawable.actor_avatar_robert_downey_jr
                            )

                    ),
                    logo_path = R.drawable.movie_avengers_end_game_logo,
                    logo_small_path = R.drawable.movie_wonder_woman_1984_logo_small
            )
    )

    fun getMovieById(id: Long?): Movie {
        return movies.find { it.id == id } ?: movies[0]
    }

}
