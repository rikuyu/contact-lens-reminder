package com.example.contactlensreminder.data.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.contactlensreminder.data.util.ChangeAppIconService
import java.util.concurrent.TimeUnit

class TickDownWorkManagerService(val context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private val changeAppIconService: ChangeAppIconService = ChangeAppIconService(context)

    private lateinit var reminderWork: PeriodicWorkRequest

    fun initTickDownWork() {
        reminderWork = PeriodicWorkRequestBuilder<TickDownWorker>(
            1, TimeUnit.DAYS
        ).apply { setInitialDelay(1, TimeUnit.DAYS) }.build()
    }

    fun startTickDownWork(elapsedDays: Int) {
        manager.enqueueUniquePeriodicWork(
            TICK_DOWN_QUEUE,
            ExistingPeriodicWorkPolicy.REPLACE,
            reminderWork
        )
        changeAppIconService.changeAppIcon(context, true, elapsedDays)
    }

    fun cancelTickDownWork() {
        manager.cancelAllWork()
        changeAppIconService.changeAppIcon(context, false, null)
    }

    companion object {
        private const val TICK_DOWN_QUEUE = "tick_down_queue"
    }
}