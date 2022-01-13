package com.example.contactlensreminder.data.repository

import android.content.Context
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.domain.util.NotificationWorkManagerService
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class ReminderRepositoryImpl(
    private val workManagerService: NotificationWorkManagerService,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        workManagerService.setNotificationTime(notificationDay = reminderValue.lensPeriod)
        sharedPreferencesManager.apply {
            saveContactLensElapsedDays(reminderValue.elapsedDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTime(reminderValue.notificationTime)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
        }
    }

    override fun startReminder() {
        workManagerService.setNotificationWork()
    }

    override fun getReminderSetting(): ReminderValue {
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val notificationTime = sharedPreferencesManager.getNotificationTime() ?: "7:00"
        val elapsedDays = sharedPreferencesManager.getContactLensElapsedDays()
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()

        return ReminderValue(
            lensPeriod = lensPeriod,
            notificationTime = notificationTime,
            elapsedDays = elapsedDays,
            isUsingContactLens = isUsingContactLens
        )
    }

    override fun cancelReminder() {
        workManagerService.cancelNotification()
    }
}