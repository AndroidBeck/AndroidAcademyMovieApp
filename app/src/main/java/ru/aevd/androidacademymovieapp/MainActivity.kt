package ru.aevd.androidacademymovieapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private val moviesListFragment = FragmentMoviesList()
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
}