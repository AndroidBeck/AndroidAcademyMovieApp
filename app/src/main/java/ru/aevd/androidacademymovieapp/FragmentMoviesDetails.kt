package ru.aevd.androidacademymovieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aevd.androidacademymovieapp.data.Movie

class FragmentMoviesDetails: Fragment() {
    private var clickListener: TransactionsFragmentClicks? = null
    private var movie: Movie = MoviesDataSource().movies[0]
    private lateinit var actorsAdapter: ActorsAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener { clickListener?.navigateBack() }
        }

        //Get info from bundle and fulfill the view
        val movieId: Int = arguments?.getInt(KEY_MOVIE_ID) ?: 1
        movie = MoviesDataSource().getMovieById(movieId)

        val name = view.findViewById<TextView>(R.id.tv_film_name)
        val ageRate: TextView = view.findViewById(R.id.tv_age_rate)
        val genres: TextView = view.findViewById(R.id.tv_genres)
        val reviewsNumber: TextView = view.findViewById(R.id.tv_reviews_number)
        val description: TextView = view.findViewById(R.id.tv_description)
        val movieLogo: ImageView = view.findViewById(R.id.img_logo)

        name.text = movie.title
        genres.text = movie.genres
        description.text = movie.description
        ageRate.text = context?.getString(R.string.age_rate, movie.)
        reviewsNumber.text = context?.getString(R.string.reviews_number, movie.reviewsNumber)

        context?.let {
            Glide.with(it)
                .load(movie.logo_path)
                .into(movieLogo)
        }

        //Create recyclerView with actors
        val actorsRecycler: RecyclerView = view.findViewById(R.id.rv_actors)
        actorsAdapter = ActorsAdapter()
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,
                false)
        actorsRecycler.layoutManager = layoutManager
        actorsRecycler.adapter = actorsAdapter

        //Performance optimization
        actorsRecycler.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        actorsAdapter.bindActors(movie.cast)
        actorsAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransactionsFragmentClicks) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    companion object {
//        private const val KEY_MOVIE_ID = "key movie id"
//        fun newInstance(movie_id: Int): FragmentMoviesDetails {
//            val args = Bundle()
//            args.putInt(KEY_MOVIE_ID, movie_id)
//            val fragment = FragmentMoviesDetails()
//            fragment.arguments = args
//            return fragment
//        }

        private const val KEY_MOVIE = "whole movie"
        fun newInstance(movie: Movie) {
            val args = Bundle()
            args.putParcelable(KEY_MOVIE, movie)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }


}

