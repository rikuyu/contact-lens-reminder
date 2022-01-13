package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class TickDownWorkManager(
    context: Context
) {
    private val manager: WorkManager = WorkManager.getInstance(context)

    private val constraints: Constraints = Constraints.Builder()
        .setRequiresCharging(false)
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresBatteryNotLow(false)
        .setRequiresDeviceIdle(false)
        .setRequiresStorageNotLow(false)
        .build()

    fun setTickDownWork() {
        val reminderWork =
            PeriodicWorkRequestBuilder<NotificationWorker>(
                15,
                TimeUnit.MINUTES,
                5,
                TimeUnit.MINUTES
            ).setConstraints(
                constraints
            ).build()
        manager.enqueue(reminderWork)
        val workId = reminderWork.id
    }
}