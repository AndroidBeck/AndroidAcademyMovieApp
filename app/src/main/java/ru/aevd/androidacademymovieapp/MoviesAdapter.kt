package ru.aevd.androidacademymovieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.data.Movie
import kotlin.math.round

class MoviesAdapter(private val recyclerClickListener: OnMoviesItemClicked):
        RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            recyclerClickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }

}

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

    fun onBind(movie: Movie) {
        name.text = movie.title
        ageRate.text = context.getString(R.string.age_rate, movie.minimumAge)
        reviewsNumber.text = context.getString(R.string.reviews_number, movie.numberOfRatings)
        durationInMinutes.text = context.getString(R.string.movie_duration_in_minutes,
                movie.runtime)
        genres.text = movie.genres.joinToString(", ") { genre ->  genre.name }
        drawMovieLogo(movie.poster)
        fulfillStars(movie.ratings)
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

    private fun drawMovieLogo(poster: String) {
        val cornerRadius = 30.0f
        val imageOption = RequestOptions()
//                .placeholder(R.drawable.ic_avatar_placeholder)
//                .fallback(R.drawable.ic_avatar_placeholder)
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

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context