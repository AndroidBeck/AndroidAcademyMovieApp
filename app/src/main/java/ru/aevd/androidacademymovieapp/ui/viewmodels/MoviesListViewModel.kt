package ru.aevd.androidacademymovieapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import ru.aevd.androidacademymovieapp.domain.GetMoviesUseCase
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import java.lang.Exception

class MoviesListViewModel(
        private val getMoviesUseCase: GetMoviesUseCase
        ): ViewModel() {

    private val _state = MutableLiveData<State>(State.Success)
    val state get() = _state

    private val _moviesResult = MutableLiveData<LoadMoviesResult>(LoadMoviesResult.OK)
    val moviesResult get() = _moviesResult

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                _movies.value = getMoviesUseCase.getMovies()
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Failed
                handleExceptions(e)
            }
        }
    }

    init {
        loadMovies()
    }

    private fun handleExceptions(e: Exception) {
        Log.e("MoviesListViewModel", "Coroutine exception: $e", e)
        _moviesResult.value = when (e) {
            is java.io.IOException -> LoadMoviesResult.Error.IO
            is HttpException -> LoadMoviesResult.Error.HTTP
            is SerializationException -> LoadMoviesResult.Error.Serialization
            else -> LoadMoviesResult.Error.Other
        }
    }

}

sealed class State {
    object Loading : State()
    object Success : State()
    object Failed: State()
}
