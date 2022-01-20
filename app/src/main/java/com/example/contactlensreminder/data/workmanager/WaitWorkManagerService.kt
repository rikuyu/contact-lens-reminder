package com.example.contactlensreminder.data.workmanager

import android.content.Context
import androidx.work.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

class WaitWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    private lateinit var waitMinutesWork: OneTimeWorkRequest

    private lateinit var waitDayHourWork: OneTimeWorkRequest

    fun initWaitMinutesWork(
        notificationDay: Int,
        notificationTimeHour: Int,
        notificationTimeMinutes: Int
    ) {
        val now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault())
        val waitMinutes = (60 - now.minute).toLong()
        val adjustHour = (24 - now.hour).toLong()

        val waitDayHour = ((notificationDay - 1) * 24 + notificationTimeHour + adjustHour).toLong()

        val waitDayHouData = Data.Builder().apply {
            putLong(WAIT_TIME_DAY_HOUR_KEY, waitDayHour)
            putLong(NOTIFICATION_TIME_MINUTES_KEY, notificationTimeMinutes.toLong())
        }.build()

        waitMinutesWork = OneTimeWorkRequestBuilder<WaitMinutesWorker>().apply {
            setInitialDelay(waitMinutes, TimeUnit.MINUTES)
            setInputData(waitDayHouData)
        }.build()
    }

    fun initWaitDayHourWork(
        waitDayHour: Long,
        notificationTimeMinutes: Long
    ) {
        val waitMinutes = Data.Builder().apply {
            putLong(NOTIFICATION_TIME_MINUTES_KEY, notificationTimeMinutes)
        }.build()

        waitDayHourWork = OneTimeWorkRequestBuilder<WaitDayHourWorker>().apply {
            setInitialDelay(waitDayHour, TimeUnit.HOURS)
            setInputData(waitMinutes)
        }.build()
    }

    fun startWaitMinutesWork() {
        manager.enqueueUniqueWork(
            WAIT_QUEUE,
            ExistingWorkPolicy.REPLACE,
            waitMinutesWork
        )
    }

    fun startWaitDayHourWork() {
        manager.enqueueUniqueWork(
            WAIT_QUEUE,
            ExistingWorkPolicy.REPLACE,
            waitDayHourWork
        )
    }

    fun cancelWaitWork() {
        manager.cancelAllWork()
    }

    companion object {
        private const val WAIT_TIME_DAY_HOUR_KEY = "wait_day_hour"
        private const val NOTIFICATION_TIME_MINUTES_KEY = "notification_time_minutes"
        private const val WAIT_QUEUE = "wait_queue"
    }
}