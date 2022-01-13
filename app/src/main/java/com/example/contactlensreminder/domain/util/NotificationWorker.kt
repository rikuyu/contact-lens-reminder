package com.example.contactlensreminder.domain.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val notificationService: NotificationService = NotificationService(context)

    override fun doWork(): Result {
        Log.d("AAAAAAAAAAAAA", "worker")
        notificationService.showNotification()
        return Result.success()
    }
}