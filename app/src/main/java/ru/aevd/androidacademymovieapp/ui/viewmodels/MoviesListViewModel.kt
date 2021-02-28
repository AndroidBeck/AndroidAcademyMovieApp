package ru.aevd.androidacademymovieapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aevd.androidacademymovieapp.domain.MoviesRepository
import ru.aevd.androidacademymovieapp.domain.Result

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val _movies = repository.getMoviesFlowFromDb().asLiveData()
    private val _state = MutableLiveData<State>(State.Success)
    private val _errorMessage = MutableLiveData<String>()

    val movies get() = _movies
    val state get() = _state
    val errorMessage get() = _errorMessage

    fun loadMovies()  = viewModelScope.launch {
        _state.value = State.Loading
        val remoteMoviesResult = repository.getMoviesResultFromNet()
        if (remoteMoviesResult is Result.Success) {
            //Save data to db. Because we use Flow it will be async loaded into UI from db
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
