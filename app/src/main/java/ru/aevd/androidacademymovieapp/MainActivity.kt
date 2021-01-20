package ru.aevd.androidacademymovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.ui.fragments.FragmentMoviesDetails
import ru.aevd.androidacademymovieapp.ui.fragments.FragmentMoviesList

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

    override fun showMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, FragmentMoviesDetails.newInstance(movie))
                addToBackStack(null)
                commit()
            }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

}