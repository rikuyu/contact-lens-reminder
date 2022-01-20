package com.example.contactlensreminder.data.util

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class TickDownWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private lateinit var reminderWork: PeriodicWorkRequest

    fun initTickDownWork() {
        reminderWork = PeriodicWorkRequestBuilder<TickDownWorker>(
            24, TimeUnit.HOURS
        ).apply { setInitialDelay(24, TimeUnit.HOURS) }.build()
    }

    fun startTickDownWork() {
        manager.enqueueUniquePeriodicWork(
            TICK_DOWN_QUEUE,
            ExistingPeriodicWorkPolicy.REPLACE,
            reminderWork
        )
    }

    fun cancelTickDownWork() {
        manager.cancelAllWork()
    }

    companion object {
        private const val TICK_DOWN_QUEUE = "tick_down_queue"
    }
}