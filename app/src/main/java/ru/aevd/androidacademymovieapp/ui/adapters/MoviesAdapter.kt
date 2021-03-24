package ru.aevd.androidacademymovieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.R
import ru.aevd.androidacademymovieapp.domain.entities.Movie

class MoviesAdapter(private val recyclerClickListener: MoviesItemClickListener):
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
        notifyDataSetChanged()
    }

}

class MovieViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.tv_film_name)
    private val movieLogoSmall: ImageView = itemView.findViewById(R.id.img_movie_logo_small)
    private val ageRate: TextView = itemView.findViewById(R.id.tv_age_rate)
    private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    private val reviewsNumber: TextView = itemView.findViewById(R.id.tv_reviews_number)
    private val durationInMinutes: TextView = itemView.findViewById(R.id.tv_film_time_length)
    private val genres: TextView = itemView.findViewById(R.id.tv_genres)

    fun onBind(movie: Movie) {
        drawMovieLogo(movie.poster)
        name.text = movie.title
        ageRate.text = context.getString(R.string.age_rate, movie.minimumAge)
        reviewsNumber.text = context.getString(R.string.reviews_number_short, movie.numberOfRatings)
        durationInMinutes.text = context.getString(
            R.string.movie_duration_in_minutes,
                movie.runtime)
        genres.text = movie.genres.joinToString(", ") { genre ->  genre.name }
        ratingBar.rating = movie.ratings / 2
    }

    private fun drawMovieLogo(poster: String) {
        val cornerRadius = context.resources.getDimensionPixelSize(R.dimen.actor_img_corner_radius).toFloat()
        val imageOption = RequestOptions()
            .placeholder(R.drawable.movie_shape)
            .transform(
                CenterCrop(),
                GranularRoundedCorners(cornerRadius, cornerRadius, 0f, 0f)
            )
        Glide.with(context)
            .load(poster)
            .apply(imageOption)
            .into(movieLogoSmall)
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context