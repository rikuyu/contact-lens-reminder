package com.example.contactlensreminder.data.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WaitDayHourWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val notificationWorkManagerService: NotificationWorkManagerService =
        NotificationWorkManagerService(context)

    override fun doWork(): Result {
        val notificationMinutes = inputData.getLong(NOTIFICATION_TIME_MINUTES_KEY, 0)
        notificationWorkManagerService.apply {
            initNotificationWork(notificationMinutes)
            startNotificationWork()
        }
        return Result.success()
    }

    companion object {
        private const val NOTIFICATION_TIME_MINUTES_KEY = "notification_time_minutes"
    }
}