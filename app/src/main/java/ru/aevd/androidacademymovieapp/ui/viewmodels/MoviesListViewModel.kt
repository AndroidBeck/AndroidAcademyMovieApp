package ru.aevd.androidacademymovieapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import kotlinx.coroutines.launch
import ru.aevd.androidacademymovieapp.domain.MoviesRepository
import ru.aevd.androidacademymovieapp.domain.Result
import ru.aevd.androidacademymovieapp.domain.entities.Movie
import ru.aevd.androidacademymovieapp.workmanager.WorkRepository

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val workRepository = WorkRepository()

    private val _state = MutableLiveData<State>(State.Success)
    private val _movies = MutableLiveData<List<Movie>>()
    private val _errorMessage = MutableLiveData<String>()

    val state get() = _state
    val movies get() = _movies
    val errorMessage get() = _errorMessage

    fun loadMovies()  = viewModelScope.launch {
        loadMoviesFromDb()
        loadMoviesFromNetwork()
    }

    private suspend fun loadMoviesFromDb() {
        val localMoviesResult = repository.getMoviesResultFromDb()
        if (localMoviesResult is Result.Success) {
            _movies.value = localMoviesResult.data
        }
    }

    private suspend fun loadMoviesFromNetwork() {
        _state.value = State.Loading
        val remoteMoviesResult = repository.getMoviesResultFromNet()
        if (remoteMoviesResult is Result.Success) {
            _movies.value = remoteMoviesResult.data
            //Save data to db
            repository.saveMoviesToDb(remoteMoviesResult.data)
            _state.value = State.Success
        } else if (remoteMoviesResult is Result.Error) {
            _state.value = State.Failed
            _errorMessage.value = remoteMoviesResult.message
        }
    }

    init {
        loadMovies()
    }

}

sealed class State {
    object Loading : State()
    object Success : State()
    object Failed: State()
}
