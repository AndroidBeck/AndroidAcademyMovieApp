package ru.aevd.androidacademymovieapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aevd.androidacademymovieapp.ui.adapters.ActorsAdapter
import ru.aevd.androidacademymovieapp.R
import ru.aevd.androidacademymovieapp.TransactionsFragmentClicks
import ru.aevd.androidacademymovieapp.entities.Movie
import ru.aevd.androidacademymovieapp.viewmodels.MoviesDetailsViewModel
import ru.aevd.androidacademymovieapp.viewmodels.MoviesDetailsViewModelFactory

class FragmentMoviesDetails: Fragment() {
    private val viewModel: MoviesDetailsViewModel by viewModels {
        MoviesDetailsViewModelFactory(
                requireNotNull(movie)
        )
    }
    private var clickListener: TransactionsFragmentClicks? = null
    private lateinit var actorsAdapter: ActorsAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private var movie: Movie? = null

    private var name: TextView? = null
    private var ageRate: TextView? = null
    private var genres: TextView? = null
    private var reviewsNumber: TextView? = null
    private var description: TextView? = null
    private var movieLogo: ImageView? = null
    private var actorsRecycler: RecyclerView? = null
    private var ratingBar: RatingBar? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener {
                clickListener?.navigateBack()
            }
        }
        findViews(view)
        movie = arguments?.getParcelable(KEY_MOVIE)
        viewModel.movie.observe(this.viewLifecycleOwner) {
            setViews(it)
            setAdapter()
            bindActors(it)
        }
    }

    private fun setViews(movie: Movie) {
        name?.text = movie.title
        description?.text = movie.overview
        ageRate?.text = getString(R.string.age_rate, movie.minimumAge)
        reviewsNumber?.text = getString(
            R.string.reviews_number,
                movie.numberOfRatings)
        ratingBar?.rating = movie.ratings / 2
        genres?.text = movie.genres
                .joinToString(", ") { genre ->  genre.name }
        movieLogo?.let {
            Glide.with(requireContext())
                    .load(movie.backdrop)
                    .into(it)
        }
    }

    private fun setAdapter() {
        actorsAdapter = ActorsAdapter()
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,
                false)
        actorsRecycler?.layoutManager = layoutManager
        actorsRecycler?.adapter = actorsAdapter
        //Performance optimization
        actorsRecycler?.setHasFixedSize(true)
    }

    private fun bindActors(movie: Movie) {
        actorsAdapter.bindActors(movie.actors)
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

    override fun onDestroyView() {
        clearViews()
        super.onDestroyView()
    }

    private fun findViews(view: View) {
        name = view.findViewById(R.id.tv_film_name)
        ageRate = view.findViewById(R.id.tv_age_rate)
        genres = view.findViewById(R.id.tv_genres)
        reviewsNumber = view.findViewById(R.id.tv_reviews_number)
        description = view.findViewById(R.id.tv_description)
        movieLogo = view.findViewById(R.id.img_logo)
        actorsRecycler = view.findViewById(R.id.rv_actors)
        ratingBar = view.findViewById(R.id.ratingBar)
    }

    private fun clearViews() {
        name = null
        ageRate = null
        genres = null
        reviewsNumber = null
        description = null
        movieLogo = null
        actorsRecycler = null
        ratingBar = null
    }

    companion object {
        private const val KEY_MOVIE = "whole movie"
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val args = Bundle()
            args.putParcelable(KEY_MOVIE, movie)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }

}

