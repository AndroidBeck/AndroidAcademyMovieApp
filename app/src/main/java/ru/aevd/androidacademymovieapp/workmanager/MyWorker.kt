package ru.aevd.androidacademymovieapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        return try {
            // Do hard work and publishProgress
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
            // Result.retry()
        }

    }
}