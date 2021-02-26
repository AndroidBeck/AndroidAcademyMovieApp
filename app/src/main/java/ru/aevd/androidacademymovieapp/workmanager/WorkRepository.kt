package ru.aevd.androidacademymovieapp.workmanager

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class WorkRepository {
//    val simpleRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
//
//    val delayedRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
//        .setInitialDelay(10L, TimeUnit.SECONDS)
//        .build()

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED) //CONNECTED, UNMETERED
        .setRequiresCharging(true)
        .build()

    val downloadWork = OneTimeWorkRequest.Builder(MyWorker::class.java)
        .setConstraints(constraints)
        //.setInputData(inputData)
        .build()

//    val periodicUploadWork = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
//        .build()
}