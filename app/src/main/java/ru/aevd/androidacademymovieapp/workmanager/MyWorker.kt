package ru.aevd.androidacademymovieapp.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.aevd.androidacademymovieapp.App

class MyWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    private val repository = (appContext as App).moviesRepository

    override suspend fun doWork(): Result {
        return try {
            Log.d("MyWorker", "Work manager start periodic downloading..")
            val remoteMoviesResult = repository.getMoviesResultFromNet()
            if (remoteMoviesResult is ru.aevd.androidacademymovieapp.domain.Result.Success) {
                repository.saveMoviesToDb(remoteMoviesResult.data)
            }
            Log.d("MyWorker", "Work manager SUCCESS work")
            Result.success()
        } catch (error: Throwable) {
            Log.d("MyWorker", "Work manager FAILED work")
            Result.failure()
            // Result.retry()
        }

    }
}