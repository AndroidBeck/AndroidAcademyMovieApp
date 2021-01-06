package ru.aevd.androidacademymovieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aevd.androidacademymovieapp.data.Movie

class MoviesListViewModel: ViewModel() {
    private val _mutableState = MutableLiveData<State>(State.Default())
    val state get() = _mutableState

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _mutableMovies

    fun loadMovies() {
        _mutableState.value = State.Loading()

        _mutableState.value = State.Default()
    }

    sealed class State {
        class Default: State()
        class Loading: State()
    }
}