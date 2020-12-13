package ru.aevd.androidacademymovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(), FragmentMoviesList.TransactionsFragmentClicks {
    private val moviesListFragment = FragmentMoviesList()
        .apply { setClickListener(this@MainActivity) }
    private val moviesDetailsFragment = FragmentMoviesDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, moviesListFragment)
                commit()
            }
    }

    override fun showDetails() {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, moviesDetailsFragment)
                commit()
            }
    }


}