package ru.aevd.androidacademymovieapp

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import ru.aevd.androidacademymovieapp.domain.DefaultMoviesRepository
import ru.aevd.androidacademymovieapp.workmanager.PERIODIC_WORK_NAME
import ru.aevd.androidacademymovieapp.workmanager.WorkRepository

class App: Application(), Configuration.Provider {

    val moviesRepository by lazy { DefaultMoviesRepository(this) }

    private val workRepository = WorkRepository()

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setMinimumLoggingLevel(Log.DEBUG)
        .build()

    override fun onCreate() {
        super.onCreate()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            PERIODIC_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRepository.periodicDownloadWork
        )
    }

}