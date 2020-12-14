package ru.aevd.androidacademymovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity: AppCompatActivity(), TransactionsFragmentClicks {
    private val moviesListFragment: FragmentMoviesList
            = FragmentMoviesList().apply { setClickListener(this@MainActivity) }
    private val moviesDetailsFragment: FragmentMoviesDetails = FragmentMoviesDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragments_container, moviesListFragment)
                    commit()
                    }
        }
    }

    override fun showDetails() {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, moviesDetailsFragment)
                addToBackStack(null)
                commit()
            }
    }

}