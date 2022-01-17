package com.example.contactlensreminder.domain.util

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class WaitWorkManagerService(context: Context) {

    private val manager: WorkManager = WorkManager.getInstance(context)

    // 残り日数 × 24 時間 + 当日 * x時 n時間待つWork
    private lateinit var waitDayHourWork: OneTimeWorkRequest

    fun initWaitWork(
        notificationDay: Int,
        notificationTimeHour: Int,
        notificationTimeMinutes: Int
    ) {
        val waitTime = (notificationDay * 24 + notificationTimeHour).toLong()
        val waitMinutes = Data.Builder().apply {
            putLong(NOTIFICATION_TIME_MINUTES_KEY, notificationTimeMinutes.toLong())
        }.build()

        waitDayHourWork = OneTimeWorkRequestBuilder<WaitWorker>().apply {
            setInitialDelay(waitTime, TimeUnit.HOURS)
            setInputData(waitMinutes)
        }.build()
    }

    fun startWaitWork() {
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
        private const val NOTIFICATION_TIME_MINUTES_KEY = "notification_time_minutes"
        private const val WAIT_QUEUE = "wait_queue"
    }
}