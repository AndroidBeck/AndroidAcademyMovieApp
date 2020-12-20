package ru.aevd.androidacademymovieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        holder.itemView.setOnClickListener() {
            recyclerClickListener.onClick(movies[position].id)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun getItem(position: Int): Movie = movies[position]

    //TODO: think - do I need this?
    fun getMovies(): List<Movie> = movies

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }

    //Perfomance optimization - adapter.setHasStableIds(true)
    //override fun getItemId(position: Int): Long = position.toLong()
}