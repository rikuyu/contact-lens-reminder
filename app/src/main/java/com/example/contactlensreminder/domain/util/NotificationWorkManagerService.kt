package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NotificationWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private lateinit var notificationWork: OneTimeWorkRequest

    fun initNotificationWork(
        notificationTimeMinutes: Long
    ) {
        notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>().apply {
            setInitialDelay(notificationTimeMinutes, TimeUnit.MINUTES)
        }.build()
    }

    fun startNotificationWork() {
        manager.enqueueUniqueWork(
            NOTIFICATION_QUEUE,
            ExistingWorkPolicy.REPLACE,
            notificationWork
        )
    }

    fun cancelNotificationWork() {
        manager.cancelAllWork()
    }

    companion object {
        private const val NOTIFICATION_QUEUE = "notification_queue"
    }
}