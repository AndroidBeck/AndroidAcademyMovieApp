package ru.aevd.androidacademymovieapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import ru.aevd.androidacademymovieapp.domain.MoviesRepository
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import java.lang.Exception

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val _state = MutableLiveData<State>(State.Success)
    val state get() = _state

    private val _moviesResult = MutableLiveData<LoadMoviesResult>(LoadMoviesResult.OK)
    val moviesResult get() = _moviesResult

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    fun loadMovies() {
        viewModelScope.launch {
            try {
                Log.d("MoviesListViewModel", "Loading from db")
                val localMovies = repository.getMoviesFromDb()
                Log.d("MoviesListViewModel", "Loading from db finished")
                if(localMovies.isNotEmpty()) {
                    _movies.value = localMovies
                    Log.d("MoviesListViewModel", "Set values from db")
                }
            } catch (e: Exception) {
                Log.e("MoviesListViewModel", "Error loading from db : $e", e)
            }

            _state.value = State.Loading
            try {
                val remoteMovies = repository.getMoviesFromNet()
                _state.value = State.Success
                _movies.value = remoteMovies

                repository.saveMoviesToDb(remoteMovies)
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
