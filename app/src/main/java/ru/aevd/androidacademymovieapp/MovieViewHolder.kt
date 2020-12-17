package ru.aevd.androidacademymovieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.tv_film_name)
    private val movieLogoSmall: ImageView = itemView.findViewById(R.id.img_movie_logo_small)
    private val ageRate: TextView = itemView.findViewById(R.id.tv_age_rate)
    private val rateStar1: ImageView = itemView.findViewById(R.id.img_star1_small)
    private val rateStar2: ImageView = itemView.findViewById(R.id.img_star2_small)
    private val rateStar3: ImageView = itemView.findViewById(R.id.img_star3_small)
    private val rateStar4: ImageView = itemView.findViewById(R.id.img_star4_small)
    private val rateStar5: ImageView = itemView.findViewById(R.id.img_star5_small)
    private val reviewsNumber: TextView = itemView.findViewById(R.id.tv_reviews_number)
    private val durationInMinutes: TextView = itemView.findViewById(R.id.tv_film_time_length)
}