package ru.aevd.androidacademymovieapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.aevd.androidacademymovieapp.*
import ru.aevd.androidacademymovieapp.entities.Movie
import ru.aevd.androidacademymovieapp.repository.GetMoviesFromNetwork
import ru.aevd.androidacademymovieapp.repository.NetworkLoad
import ru.aevd.androidacademymovieapp.ui.adapters.MoviesAdapter
import ru.aevd.androidacademymovieapp.ui.adapters.OnMoviesItemClicked
import ru.aevd.androidacademymovieapp.viewmodels.MoviesListViewModel
import ru.aevd.androidacademymovieapp.viewmodels.MoviesListViewModelFactory
import ru.aevd.androidacademymovieapp.viewmodels.State

class FragmentMoviesList: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
            GetMoviesFromNetwork(
                NetworkLoad()
//                TODO 4: remove commented old code
//                GetMoviesFromAssets(
//                        MoviePersistent(requireContext().applicationContext)
                )
        )
    }

    private var recycler: RecyclerView? = null
    private lateinit var adapter: MoviesAdapter
    private var clickListener: TransactionsFragmentClicks? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews(view)
        adapter = MoviesAdapter(recyclerClickListener)
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler?.adapter = adapter
        //observe some liveData using  ViewModel
        viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.state.observe(this.viewLifecycleOwner, this::showLoading)
    }

    private val recyclerClickListener = object: OnMoviesItemClicked {
        override fun onClick(movie: Movie) {
            clickListener?.showMovieDetails(movie)
        }
    }

    private fun updateAdapter(moviesList: List<Movie>) {
        adapter.bindMovies(moviesList)
    }

    private fun showLoading(state: State) {
        progressBar?.isVisible = state == State.Loading
        recycler?.isVisible = state == State.Ready
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
        progressBar = view.findViewById(R.id.pb_loading)
        recycler = view.findViewById(R.id.rv_movies)
    }

    private fun clearViews() {
        progressBar = null
        recycler = null
    }

}
