package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        return Result.success()
    }
}