package com.example.contactlensreminder.data.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.contactlensreminder.data.util.ChangeAppIconService
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TickDownWorkManagerService(val context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private val changeAppIconService: ChangeAppIconService = ChangeAppIconService(context)

    private lateinit var reminderWork: PeriodicWorkRequest

    fun initTickDownWork() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("hh", Locale.ENGLISH)
        calendar.timeZone = TimeZone.getDefault()
        val hour = (24 - simpleDateFormat.format(calendar.time).toInt()).toLong()
        reminderWork = PeriodicWorkRequestBuilder<TickDownWorker>(
            24, TimeUnit.HOURS
        ).apply { setInitialDelay(hour, TimeUnit.HOURS) }.build()
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