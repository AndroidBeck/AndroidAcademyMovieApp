package ru.aevd.androidacademymovieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aevd.androidacademymovieapp.R
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.ui.fragments.MoviesDetailsFragment
import ru.aevd.androidacademymovieapp.ui.fragments.MoviesListFragment

class MainActivity: AppCompatActivity(), FragmentClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragments_container, MoviesListFragment())
                    commit()
                }
        }
    }

    override fun onShowDetailsClick(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, MoviesDetailsFragment.newInstance(movie))
                addToBackStack(null)
                commit()
            }
    }

    override fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

}