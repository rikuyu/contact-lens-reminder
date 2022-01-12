package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class NotificationWorkManagerService(
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

    private var notificationTimeData: Data? = null

    fun setNotificationTime(
        lensPeriod: Int,
        notificationTime: String,
        currentTimeHour: Int
    ) {
        notificationTimeData = Data.Builder()
            .putInt(CURRENT_HOUR_KEY, currentTimeHour)
            .build()
    }

    fun setNotificationWork() {
        notificationTimeData?.let {
            val reminderWork =
                PeriodicWorkRequestBuilder<NotificationWorker>(
                    15,
                    TimeUnit.MINUTES,
                    5,
                    TimeUnit.MINUTES
                ).setConstraints(
                    constraints
                ).setInputData(it).build()
            manager.enqueue(reminderWork)
            val workId = reminderWork.id
        }
    }

    companion object {
        const val CURRENT_HOUR_KEY = "current_hour_key"
        const val NOTIFICATION_MINUTE_KEY = "notification_minute_key"
    }
}