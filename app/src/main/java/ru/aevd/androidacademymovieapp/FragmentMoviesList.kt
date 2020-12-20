package ru.aevd.androidacademymovieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList: Fragment() {

    private var clickListener: TransactionsFragmentClicks? = null
    private lateinit var adapter: MoviesAdapter

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
        //Perfomance optimization
        //recycler.setHasFixedSize(true)
        //adapter.setHasStableIds(true)
    }

    private val recyclerClickListener = object: OnMoviesItemClicked {
        override fun onClick(movie_id: Long) {
            clickListener?.showMovieDetails(movie_id)
        }
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindMovies(MoviesDataSource().movies)
        adapter.notifyDataSetChanged()
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

}