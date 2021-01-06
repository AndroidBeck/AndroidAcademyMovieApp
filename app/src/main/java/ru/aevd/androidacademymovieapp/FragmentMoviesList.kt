package ru.aevd.androidacademymovieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.aevd.androidacademymovieapp.data.loadMovies
import ru.aevd.androidacademymovieapp.data.Movie

class FragmentMoviesList: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels { ViewModelFactory() }
    private var clickListener: TransactionsFragmentClicks? = null
    private lateinit var adapter: MoviesAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var movies: List<Movie> = listOf()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.rv_movies)
        adapter = MoviesAdapter(recyclerClickListener)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter
    }

    private val recyclerClickListener = object: OnMoviesItemClicked {
        override fun onClick(movie: Movie) {
            clickListener?.showMovieDetails(movie)
        }
    }

    override fun onStart() {
        super.onStart()
        coroutineScope.launch {
            movies = loadMovies(requireContext())
            updateData()
        }
    }

    private fun updateData() {
        adapter.bindMovies(movies)
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
        coroutineScope.cancel()
        super.onDestroyView()
    }

}