package ru.aevd.androidacademymovieapp.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.aevd.androidacademymovieapp.App

class MyWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    private val repository = (appContext as App).moviesRepository
    override suspend fun doWork(): Result {
        return try {
            // Do hard work and publishProgress
            val remoteMoviesResult = repository.getMoviesResultFromNet()
            if (remoteMoviesResult is ru.aevd.androidacademymovieapp.domain.Result.Success) {
                repository.saveMoviesToDb(remoteMoviesResult.data)
            }
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
            // Result.retry()
        }

    }
}