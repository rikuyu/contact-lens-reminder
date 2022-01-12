package com.example.contactlensreminder.data.repository

import android.content.Context
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.domain.util.NotificationWorkManagerService
import com.example.contactlensreminder.domain.util.getLocalHour
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class ReminderRepositoryImpl(private val context: Context) : ReminderRepository {

    private val workManagerService: NotificationWorkManagerService = NotificationWorkManagerService(context)

    private val sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager()

    override fun setNotificationData(reminderValue: ReminderValue) {
        val currentTime = getLocalHour()
        workManagerService.setNotificationTime(
            lensPeriod = reminderValue.lensPeriod,
            notificationTime = reminderValue.notificationTime,
            currentTimeHour = currentTime
        )
    }

    override fun setReminder() {
    }

    override fun getReminderSetting(): ReminderValue {
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod(context)
        val notificationTime = sharedPreferencesManager.getNotificationTime(context) ?: "7:00"
        return ReminderValue(
            lensPeriod = lensPeriod,
            notificationTime = notificationTime
        )
    }
}