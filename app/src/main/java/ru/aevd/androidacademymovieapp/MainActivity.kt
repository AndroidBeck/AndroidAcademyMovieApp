package ru.aevd.androidacademymovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity: AppCompatActivity(), TransactionsFragmentClicks {
    private val moviesListFragment: FragmentMoviesList = FragmentMoviesList()

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

    override fun showMovieDetails(movie_id: Long) {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, FragmentMoviesDetails.newInstance(movie_id))
                addToBackStack(null)
                commit()
            }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

}