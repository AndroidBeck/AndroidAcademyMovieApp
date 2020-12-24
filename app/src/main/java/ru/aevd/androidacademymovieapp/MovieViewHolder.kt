package ru.aevd.androidacademymovieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions

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
        name.text = movie.name
        ageRate.text = context.getString(R.string.age_rate, movie.ageRate)
        reviewsNumber.text = context.getString(R.string.reviews_number, movie.reviewsNumber)
        durationInMinutes.text = context.getString(R.string.movie_duration_in_minutes,  movie.timeDurationInMinutes)
        genres.text = movie.genres
        liked.visibility = when (movie.isLiked) {
            true -> View.VISIBLE
            false -> View.GONE
        }

        val cornerRadius = 30.0f
        val imageOption = RequestOptions()
                .transform(
                        CenterInside()
                        , GranularRoundedCorners(
                                cornerRadius, cornerRadius, 0f, 0f)
                )
        Glide.with(context)
                .load(movie.logo_small_path)
                .apply(imageOption)
                .into(movieLogoSmall)

        //Fulfill stars
        for (i in 0..4) {
            val into = rateStars[i]
            val starImg = if (movie.rateInStars > i)
                R.drawable.star_icon_full_small
            else R.drawable.star_icon_empty_small
            Glide.with(context)
                    .load(starImg)
                    .into(into)
        }
    }


}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
