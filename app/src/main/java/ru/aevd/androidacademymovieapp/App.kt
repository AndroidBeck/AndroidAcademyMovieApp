package ru.aevd.androidacademymovieapp

import android.app.Application
import ru.aevd.androidacademymovieapp.domain.DefaultMoviesRepository

class App: Application() {

    val moviesRepository by lazy { DefaultMoviesRepository(this) }
    //val appContext = this.applicationContext
}