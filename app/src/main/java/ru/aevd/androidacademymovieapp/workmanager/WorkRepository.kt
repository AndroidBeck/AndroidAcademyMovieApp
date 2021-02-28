package ru.aevd.androidacademymovieapp.workmanager

import android.os.Build
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class WorkRepository {
//    val simpleRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
//


//    val downloadWork = OneTimeWorkRequest.Builder(MyWorker::class.java)
//        .setConstraints(constraints)
//        .setInputData(inputData)
//        .build()

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED) //CONNECTED, UNMETERED
        .setRequiresCharging(true)
        .apply {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setRequiresDeviceIdle(true)
        }
        .build()

    val delayedRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
        .setInitialDelay(60L, TimeUnit.SECONDS)
        .build()

    val periodicDownloadWork = PeriodicWorkRequest
        //.Builder(MyWorker::class.java, 8, TimeUnit.HOURS)
        //.setConstraints(constraints)
        //.setInitialDelay(1, TimeUnit.HOURS)
        .Builder(MyWorker::class.java, 90, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .setInitialDelay(60, TimeUnit.SECONDS)
        .build()
}

const val PERIODIC_WORK_NAME = "Periodic Download Work"