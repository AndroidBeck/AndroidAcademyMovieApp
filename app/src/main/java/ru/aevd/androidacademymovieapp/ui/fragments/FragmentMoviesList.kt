package ru.aevd.androidacademymovieapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.aevd.androidacademymovieapp.*
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.domain.GetMoviesFromNetwork
import ru.aevd.androidacademymovieapp.domain.NetworkLoad
import ru.aevd.androidacademymovieapp.ui.TransactionsFragmentClicks
import ru.aevd.androidacademymovieapp.ui.adapters.MoviesAdapter
import ru.aevd.androidacademymovieapp.ui.adapters.OnMoviesItemClicked
import ru.aevd.androidacademymovieapp.ui.viewmodels.MoviesListViewModel
import ru.aevd.androidacademymovieapp.ui.viewmodels.MoviesListViewModelFactory
import ru.aevd.androidacademymovieapp.ui.viewmodels.LoadMoviesResult
import ru.aevd.androidacademymovieapp.ui.viewmodels.State

class FragmentMoviesList: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
            GetMoviesFromNetwork(NetworkLoad())
//            GetMoviesFromAssets(MoviePersistent(requireContext().applicationContext)
        )
    }

    private var recycler: RecyclerView? = null
    private lateinit var adapter: MoviesAdapter
    private var clickListener: TransactionsFragmentClicks? = null
    private var progressBar: ProgressBar? = null
    private var errorMessage: TextView? = null
    private var reloadButton: Button? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews(view)
        reloadButton?.setOnClickListener { viewModel.loadMovies() }
        adapter = MoviesAdapter(recyclerClickListener)
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler?.adapter = adapter
        //observe some liveData using  ViewModel
        viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.state.observe(this.viewLifecycleOwner, this::showLoading)
        viewModel.moviesResult.observe(this.viewLifecycleOwner, this::setErrorText)
    }

    private fun setErrorText(moviesResult: LoadMoviesResult) {
        Log.d("FragmentMoviesList", "result = $moviesResult")
        errorMessage?.text = when(moviesResult) {
            LoadMoviesResult.Error.IO -> getString(R.string.load_error_io)
            LoadMoviesResult.Error.HTTP -> getString(R.string.load_error_http)
            LoadMoviesResult.Error.Serialization -> getString(R.string.load_error_serialization)
            else -> getString(R.string.load_error_unknown)
        }
    }

    private fun showLoading(state: State) {
        Log.d("FragmentMoviesList", "showLoading(), result = $state")
        progressBar?.isVisible = state == State.Loading
        recycler?.isVisible = state == State.Success
        errorMessage?.isVisible = state == State.Failed
        reloadButton?.isVisible = state == State.Failed
    }

    private fun updateAdapter(moviesList: List<Movie>) {
        adapter.bindMovies(moviesList)
    }

    private val recyclerClickListener = object: OnMoviesItemClicked {
        override fun onClick(movie: Movie) {
            clickListener?.showMovieDetails(movie)
        }
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
        errorMessage = view.findViewById(R.id.tv_errorMessage)
        reloadButton = view.findViewById(R.id.but_reload)
    }

    private fun clearViews() {
        progressBar = null
        recycler = null
        errorMessage = null
        reloadButton = null
    }

}
