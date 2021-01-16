package ru.aevd.androidacademymovieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aevd.androidacademymovieapp.repository.GetMoviesUseCase
import ru.aevd.androidacademymovieapp.entities.Movie

@Suppress("unused")
class MoviesListViewModel(
        private val getMoviesUseCase: GetMoviesUseCase
        ): ViewModel() {

    private val _state = MutableLiveData<State>(State.Default())
    val state get() = _state

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    private fun loadMovies() {
        _state.value = State.Loading()
        viewModelScope.launch {
            _movies.value = getMoviesUseCase.getMovies()
        }
        _state.value = State.Default()
    }

    init {
        loadMovies()
    }

    sealed class State {
        class Default: State()
        class Loading: State()
    }
}