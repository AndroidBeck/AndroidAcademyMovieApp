package ru.aevd.androidacademymovieapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import ru.aevd.androidacademymovieapp.domain.MoviesRepository
import ru.aevd.androidacademymovieapp.domain.Result
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import java.lang.Exception

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val _state = MutableLiveData<State>(State.Success)
    val state get() = _state

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage get() = _errorMessage

    fun loadMovies() {
        viewModelScope.launch {
            //Get data from Db
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

            //Load data from network
            _state.value = State.Loading
            val remoteMoviesResult = repository.getMoviesResultFromNet()
            if (remoteMoviesResult is Result.Success) {
                _movies.value = remoteMoviesResult.data
                repository.saveMoviesToDb(remoteMoviesResult.data)
                _state.value = State.Success
            } else if (remoteMoviesResult is Result.Error) {
                _state.value = State.Failed
                setErrorMessage(remoteMoviesResult.e)
            }
        }
    }

    init {
        loadMovies()
    }

    private fun setErrorMessage(e: Exception) {
        _errorMessage.value = when (e) {
            is java.io.IOException, is HttpException -> ErrorMessage.NetworkError
            is SerializationException -> ErrorMessage.SerializationError
            else -> ErrorMessage.OtherError
        }
    }

}

sealed class ErrorMessage {
    object NetworkError: ErrorMessage()
    object SerializationError: ErrorMessage()
    object OtherError: ErrorMessage()
}

sealed class State {
    object Loading : State()
    object Success : State()
    object Failed: State()
}


