package com.example.contactlensreminder.data.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val notificationService: NotificationService = NotificationService(context)

    override fun doWork(): Result {
        notificationService.showNotification()
        return Result.success()
    }
}