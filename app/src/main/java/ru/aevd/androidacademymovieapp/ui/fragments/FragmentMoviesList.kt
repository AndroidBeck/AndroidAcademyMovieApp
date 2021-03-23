package ru.aevd.androidacademymovieapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.aevd.androidacademymovieapp.*
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.ui.TransactionsFragmentClicks
import ru.aevd.androidacademymovieapp.ui.adapters.MoviesAdapter
import ru.aevd.androidacademymovieapp.ui.adapters.OnMoviesItemClicked
import ru.aevd.androidacademymovieapp.ui.viewmodels.MoviesListViewModel
import ru.aevd.androidacademymovieapp.ui.viewmodels.MoviesListViewModelFactory
import ru.aevd.androidacademymovieapp.ui.viewmodels.State

class FragmentMoviesList: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
           appContext = requireContext().applicationContext
        )
    }

    private var recycler: RecyclerView? = null
    private lateinit var adapter: MoviesAdapter
    private var clickListener: TransactionsFragmentClicks? = null
    private var progressBar: ProgressBar? = null
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
        viewModel.errorMessage.observe(this.viewLifecycleOwner, this::showErrorMsg)
    }

    private fun showErrorMsg(errorMsg: String) {
        Log.d("FragmentMoviesList", "setErrorText(), result = $errorMsg")
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state: State) {
        Log.d("FragmentMoviesList", "showLoading(), result = $state")
        progressBar?.isVisible = state == State.Loading
        recycler?.isVisible = true //state == State.Success
        reloadButton?.isVisible = state == State.Failed
    }

    private fun updateAdapter(moviesList: List<Movie>) {
        Log.d("FragmentMoviesList", "updateAdapters(), moviesList = $moviesList")
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
        reloadButton = view.findViewById(R.id.but_reload)
    }

    private fun clearViews() {
        progressBar = null
        recycler = null
        reloadButton = null
    }

}
