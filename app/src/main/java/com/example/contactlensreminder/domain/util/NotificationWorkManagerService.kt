package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class NotificationWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private var notificationDay by Delegates.notNull<Long>()

    lateinit var notificationWork: WorkRequest

    fun setNotificationTime(notificationDay: Int) {
        this@NotificationWorkManagerService.notificationDay = notificationDay.toLong()
    }

    fun setNotificationWork() {
        notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>().apply {
            setInitialDelay(notificationDay, TimeUnit.MINUTES)
        }.build()
        manager.enqueue(notificationWork)
    }

    fun cancelNotification() {
        val id = notificationWork.id
        manager.cancelWorkById(id)
    }
}