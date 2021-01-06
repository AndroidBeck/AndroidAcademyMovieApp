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
import ru.aevd.androidacademymovieapp.data.Movie
import ru.aevd.androidacademymovieapp.viewmodels.MoviesListViewModel
import ru.aevd.androidacademymovieapp.viewmodels.MoviesListViewModelFactory

class FragmentMoviesList: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
                GetMoviesUsingContextUseCase(
                        MoviePersistent(requireContext().applicationContext)
                )
        )
    }
    private var clickListener: TransactionsFragmentClicks? = null
    private lateinit var adapter: MoviesAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

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
        //observe some liveData using  ViewModel
        viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
    }

    private val recyclerClickListener = object: OnMoviesItemClicked {
        override fun onClick(movie: Movie) {
            clickListener?.showMovieDetails(movie)
        }
    }

    private fun updateAdapter(moviesList: List<Movie>) {
        adapter.bindMovies(moviesList)
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