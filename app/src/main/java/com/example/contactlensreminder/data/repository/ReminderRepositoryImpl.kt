package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.sharedpreferences.SharedPreferencesManager
import com.example.contactlensreminder.data.alarm.AlarmManagerService
import com.example.contactlensreminder.data.workmanager.TickDownWorkManagerService
import com.example.contactlensreminder.domain.ReminderValue
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.presentation.util.getExpirationDate

class ReminderRepositoryImpl(
    private val tickDownWorkManagerService: TickDownWorkManagerService,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val alarmManagerService: AlarmManagerService
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        sharedPreferencesManager.apply {
            saveContactLensElapsedDays(reminderValue.elapsedDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
            saveLensExchangeDay(getExpirationDate(reminderValue.lensPeriod))
        }
        if (sharedPreferencesManager.getIsUsingContactLens()) {
            tickDownWorkManagerService.initTickDownWork()
        }
    }

    override fun startReminder(elapsedDays: Int) {
        if (sharedPreferencesManager.getIsUseNotification()) {
            alarmManagerService.initAlarm()
        }
        tickDownWorkManagerService.startTickDownWork(elapsedDays)
    }

    override fun getReminderSetting(): ReminderValue {
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val notificationTimeHour = sharedPreferencesManager.getNotificationTimeHour()
        val notificationTimeMinute = sharedPreferencesManager.getNotificationTimeMinute()
        val elapsedDays = sharedPreferencesManager.getContactLensElapsedDays()
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        val isUseNotification = sharedPreferencesManager.getIsUseNotification()
        val exchangeDay = sharedPreferencesManager.getLensExchangeDay() ?: getExpirationDate(lensPeriod)

        return ReminderValue(
            lensPeriod = lensPeriod,
            exchangeDay = exchangeDay,
            notificationTimeHour = notificationTimeHour,
            notificationTimeMinute = notificationTimeMinute,
            elapsedDays = elapsedDays,
            isUsingContactLens = isUsingContactLens,
            isUseNotification = isUseNotification
        )
    }

    override fun cancelReminder() {
        if (sharedPreferencesManager.getIsUseNotification()) {
            alarmManagerService.cancelAlarm()
        }
        tickDownWorkManagerService.cancelTickDownWork()
    }
}