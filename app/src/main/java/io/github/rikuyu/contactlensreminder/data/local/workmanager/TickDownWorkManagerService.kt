package io.github.rikuyu.contactlensreminder.data.local.workmanager

import android.content.Context
import androidx.work.*
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TickDownWorkManagerService(val context: Context) {

    private val managerFirst: WorkManager = WorkManager.getInstance(context)
    private val managerSecond: WorkManager = WorkManager.getInstance(context)

    private val changeAppIconService: ChangeAppIconService = ChangeAppIconService(context)

    private lateinit var firstOneTimeTickDownWork: OneTimeWorkRequest
    private lateinit var secondPeriodicTickDownWork: PeriodicWorkRequest

    fun initTickDownWork() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("hh", Locale.ENGLISH)
        calendar.timeZone = TimeZone.getDefault()
        val hour = (24 - simpleDateFormat.format(calendar.time).toInt()).toLong()
        firstOneTimeTickDownWork = OneTimeWorkRequestBuilder<TickDownWorker>()
            .setInitialDelay(hour, TimeUnit.HOURS)
            .build()
        secondPeriodicTickDownWork = PeriodicWorkRequestBuilder<TickDownWorker>(
            24, TimeUnit.HOURS,
            15, TimeUnit.MINUTES
        ).setInitialDelay(hour, TimeUnit.HOURS).build()
    }

    fun startTickDownWork(elapsedDays: Int) {
        managerFirst.enqueueUniqueWork(
            TICK_DOWN_FIRST_WORK,
            ExistingWorkPolicy.KEEP,
            firstOneTimeTickDownWork
        )
        managerSecond.enqueueUniquePeriodicWork(
            TICK_DOWN_SECOND_WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            secondPeriodicTickDownWork
        )
        changeAppIconService.changeAppIcon(context, true, elapsedDays)
    }

    fun cancelTickDownWork() {
        managerFirst.cancelAllWork()
        changeAppIconService.changeAppIcon(context, false, null)
    }

    companion object {
        private const val TICK_DOWN_FIRST_WORK = "tick_down_first_work"
        private const val TICK_DOWN_SECOND_WORK = "tick_down_second_work"
    }
}