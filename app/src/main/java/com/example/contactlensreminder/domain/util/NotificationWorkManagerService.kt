package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class NotificationWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private var notificationDay by Delegates.notNull<Long>()

    private var notificationWork: WorkRequest =
        OneTimeWorkRequestBuilder<NotificationWorker>().apply {
            setInitialDelay(15, TimeUnit.MINUTES)
        }.build()

    fun setNotificationTime(notificationDay: Int) {
        this@NotificationWorkManagerService.notificationDay = notificationDay.toLong()
    }

    fun setNotificationWork() {
        manager.enqueue(notificationWork)
    }

    fun cancelNotification() {
        val id = notificationWork.id
        manager.cancelWorkById(id)
    }
}