package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.domain.util.NotificationWorkManagerService
import com.example.contactlensreminder.domain.util.WaitWorkManagerService
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class ReminderRepositoryImpl(
    private val waitWorkManagerService: WaitWorkManagerService,
    private val notificationWorkManagerService: NotificationWorkManagerService,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        waitWorkManagerService.initWaitWork(
            notificationDay = reminderValue.lensPeriod,
            notificationTimeHour = reminderValue.notificationTimeHour,
            notificationTimeMinutes = reminderValue.notificationTimeMinute
        )
        sharedPreferencesManager.apply {
            saveContactLensElapsedDays(reminderValue.elapsedDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
        }
    }

    override fun startReminder() {
        waitWorkManagerService.startWaitWork()
    }

    override fun getReminderSetting(): ReminderValue {
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val notificationTimeHour = sharedPreferencesManager.getNotificationTimeHour()
        val notificationTimeMinute = sharedPreferencesManager.getNotificationTimeMinute()
        val elapsedDays = sharedPreferencesManager.getContactLensElapsedDays()
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()

        return ReminderValue(
            lensPeriod = lensPeriod,
            notificationTimeHour = notificationTimeHour,
            notificationTimeMinute = notificationTimeMinute,
            elapsedDays = elapsedDays,
            isUsingContactLens = isUsingContactLens
        )
    }

    override fun cancelReminder() {
        waitWorkManagerService.cancelWaitWork()
        notificationWorkManagerService.cancelNotificationWork()
    }
}