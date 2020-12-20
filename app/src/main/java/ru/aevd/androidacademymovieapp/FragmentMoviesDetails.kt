package ru.aevd.androidacademymovieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails: Fragment() {

    private var clickListener: TransactionsFragmentClicks? = null
    private var movie: Movie? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener { clickListener?.navigateBack() }
        }

        var movieId: Long = arguments?.getLong(KEY_MOVIE_ID) ?: 1
        movie = MoviesDataSource().getMovie(movieId)
        var name = view.findViewById<TextView>(R.id.tv_film_name)
        name.text = movie?.name

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
        private const val KEY_MOVIE_ID = "key movie id"
        fun newInstance(movie_id: Long): FragmentMoviesDetails {
            val args = Bundle()
            args.putLong(KEY_MOVIE_ID, movie_id)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }


}

