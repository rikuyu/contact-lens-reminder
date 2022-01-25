package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.data.workmanager.NotificationWorkManagerService
import com.example.contactlensreminder.data.workmanager.TickDownWorkManagerService
import com.example.contactlensreminder.data.workmanager.WaitWorkManagerService
import com.example.contactlensreminder.domain.ReminderValue
import com.example.contactlensreminder.domain.repository.ReminderRepository

class ReminderRepositoryImpl(
    private val waitWorkManagerService: WaitWorkManagerService,
    private val notificationWorkManagerService: NotificationWorkManagerService,
    private val tickDownWorkManagerService: TickDownWorkManagerService,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        sharedPreferencesManager.apply {
            saveContactLensElapsedDays(reminderValue.elapsedDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
        }
        if (sharedPreferencesManager.getIsUseNotification() && sharedPreferencesManager.getIsUsingContactLens()) {
            waitWorkManagerService.initWaitMinutesWork(
                notificationPeriod = reminderValue.lensPeriod,
                notificationTimeHour = reminderValue.notificationTimeHour,
                notificationTimeMinutes = reminderValue.notificationTimeMinute,
                notificationDay = sharedPreferencesManager.getNotificationDay()
            )
        }
        if (sharedPreferencesManager.getIsUsingContactLens()) {
            tickDownWorkManagerService.initTickDownWork()
        }
    }

    override fun startReminder(elapsedDays: Int) {
        if (sharedPreferencesManager.getIsUseNotification()) {
            waitWorkManagerService.startWaitMinutesWork()
            tickDownWorkManagerService.startTickDownWork(elapsedDays)
        }
    }

    override fun getReminderSetting(): ReminderValue {
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val notificationTimeHour = sharedPreferencesManager.getNotificationTimeHour()
        val notificationTimeMinute = sharedPreferencesManager.getNotificationTimeMinute()
        val elapsedDays = sharedPreferencesManager.getContactLensElapsedDays()
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        val isUseNotification = sharedPreferencesManager.getIsUseNotification()

        return ReminderValue(
            lensPeriod = lensPeriod,
            notificationTimeHour = notificationTimeHour,
            notificationTimeMinute = notificationTimeMinute,
            elapsedDays = elapsedDays,
            isUsingContactLens = isUsingContactLens,
            isUseNotification = isUseNotification
        )
    }

    override fun cancelReminder() {
        waitWorkManagerService.cancelWaitWork()
        notificationWorkManagerService.cancelNotificationWork()
        tickDownWorkManagerService.cancelTickDownWork()
    }
}