package ru.aevd.androidacademymovieapp.ui.viewmodels

sealed class LoadMoviesResult {
    object OK: LoadMoviesResult()
    //object EmptyResult: LoadMoviesResult()
    sealed class Error: LoadMoviesResult() {
        object IO: Error()
        object HTTP: Error()
        object Serialization: Error()
        object Other: Error()
    }
}

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T): Result<T>()
    data class Error(val message: String): Result<Nothing>()

}
