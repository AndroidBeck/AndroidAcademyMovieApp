package ru.aevd.androidacademymovieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.data.Genre
import ru.aevd.androidacademymovieapp.data.Movie
import kotlin.math.round

class MovieViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.tv_film_name)
    private val movieLogoSmall: ImageView = itemView.findViewById(R.id.img_movie_logo_small)
    private val ageRate: TextView = itemView.findViewById(R.id.tv_age_rate)
    private val rateStars: Array<ImageView> = arrayOf(
            itemView.findViewById(R.id.img_star1_small),
            itemView.findViewById(R.id.img_star2_small),
            itemView.findViewById(R.id.img_star3_small),
            itemView.findViewById(R.id.img_star4_small),
            itemView.findViewById(R.id.img_star5_small)
    )
    private val reviewsNumber: TextView = itemView.findViewById(R.id.tv_reviews_number)
    private val durationInMinutes: TextView = itemView.findViewById(R.id.tv_film_time_length)
    private val genres: TextView = itemView.findViewById(R.id.tv_genres)
    private val liked: ImageView = itemView.findViewById(R.id.img_like)

    fun onBind(movie: Movie) {
        name.text = movie.title
        ageRate.text = context.getString(R.string.age_rate, movie.minimumAge)
        reviewsNumber.text = context.getString(R.string.reviews_number, movie.numberOfRatings)
        durationInMinutes.text = context.getString(R.string.movie_duration_in_minutes,
            movie.runtime)
        genres.text = gerGenresText(movie.genres)
        drawMovieLogo(movie.poster)
        fulfillStars(movie.ratings)
        drawLike()
    }

    private fun fulfillStars(ratings: Float) {
        val rateInStars: Float = round(ratings  / 2)
        for (i in 0..4) {
            val into = rateStars[i]
            val starImg = if (rateInStars > i)
                R.drawable.star_icon_full_small
            else R.drawable.star_icon_empty_small
            Glide.with(context)
                .load(starImg)
                .into(into)
        }
    }

    //TODO: make it more elegant
    private fun gerGenresText(genres: List<Genre>): String {
        var genresText = ""
        for(genre in genres) {
            genresText += genre
            genresText += ", "
        }
        return genresText
    }

    private fun drawMovieLogo(poster: String) {
        val cornerRadius = 30.0f
        val imageOption = RequestOptions()
            .transform(
                CenterInside(),
                GranularRoundedCorners(
                    cornerRadius, cornerRadius, 0f, 0f)
            )
        Glide.with(context)
            .load(poster)
            .apply(imageOption)
            .into(movieLogoSmall)
    }

    //TODO: draw like
    private fun drawLike() {
//        liked.visibility = when (movie.isLiked) {
//            true -> View.VISIBLE
//            false -> View.GONE
//        }
    }

}




private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
