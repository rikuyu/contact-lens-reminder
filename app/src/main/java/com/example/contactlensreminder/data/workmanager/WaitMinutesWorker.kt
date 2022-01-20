package com.example.contactlensreminder.data.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WaitMinutesWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val waitWorkManagerService: WaitWorkManagerService = WaitWorkManagerService(context)

    override fun doWork(): Result {
        val waitDayHour = inputData.getLong(WAIT_TIME_DAY_HOUR_KEY, 0)
        val notificationMinutes = inputData.getLong(NOTIFICATION_TIME_MINUTES_KEY, 0)

        waitWorkManagerService.apply {
            initWaitDayHourWork(
                waitDayHour = waitDayHour,
                notificationTimeMinutes = notificationMinutes
            )
            startWaitDayHourWork()
        }

        return Result.success()
    }

    companion object {
        private const val NOTIFICATION_TIME_MINUTES_KEY = "notification_time_minutes"
        private const val WAIT_TIME_DAY_HOUR_KEY = "wait_day_hour"
    }
}