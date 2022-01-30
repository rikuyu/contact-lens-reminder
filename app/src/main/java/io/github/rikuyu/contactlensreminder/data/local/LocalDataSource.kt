package io.github.rikuyu.contactlensreminder.data.local

import io.github.rikuyu.contactlensreminder.data.local.alarm.AlarmManagerService
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.local.workmanager.TickDownWorkManagerService
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.presentation.util.getExpirationDate

class LocalDataSource(
    private val tickDownWorkManagerService: TickDownWorkManagerService,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val alarmManagerService: AlarmManagerService
) : DataSource {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        sharedPreferencesManager.apply {
            saveContactLensRemainingDays(reminderValue.lensRemainingDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
            saveLensExchangeDay(getExpirationDate(reminderValue.lensPeriod))
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
        val elapsedDays = sharedPreferencesManager.getContactLensRemainingDays()
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        val isUseNotification = sharedPreferencesManager.getIsUseNotification()
        val exchangeDay = sharedPreferencesManager.getLensExchangeDay() ?: getExpirationDate(lensPeriod)

        return ReminderValue(
            lensPeriod = lensPeriod,
            exchangeDay = exchangeDay,
            notificationTimeHour = notificationTimeHour,
            notificationTimeMinute = notificationTimeMinute,
            lensRemainingDays = elapsedDays,
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

    override fun saveAllSetting(settingValue: SettingValue) {
        val lensType = settingValue.lensType
        val lensPeriod = settingValue.lensPeriod
        val isUseNotification = settingValue.isUseNotification
        val notificationDay = settingValue.notificationDay
        val notificationTimeHour = settingValue.notificationTimeHour
        val notificationTimeMinute = settingValue.notificationTimeMinute
        val isShowLensPowerSection = settingValue.isShowLensPowerSection
        val leftLensPower = settingValue.leftLensPower
        val rightLensPower = settingValue.rightLensPower
        val elapsedDays = settingValue.lensPeriod

        sharedPreferencesManager.apply {
            saveContactLensType(lensType)
            saveContactLensPeriod(lensPeriod)
            saveIsUseNotification(isUseNotification)
            saveNotificationDay(notificationDay)
            saveNotificationTimeHour(notificationTimeHour)
            saveNotificationTimeMinute(notificationTimeMinute)
            saveIsShowContactLensPowerSection(isShowLensPowerSection)
            saveLeftContactLensPower(leftLensPower.toString())
            saveRightContactLensPower(rightLensPower.toString())
            saveContactLensRemainingDays(elapsedDays)
        }
    }

    override fun getAllSetting(): SettingValue {
        val lensType = sharedPreferencesManager.getContactLensType()
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val isUseNotification = sharedPreferencesManager.getIsUseNotification()
        val notificationDay = sharedPreferencesManager.getNotificationDay()
        val notificationTimeHour = sharedPreferencesManager.getNotificationTimeHour()
        val notificationTimeMinute = sharedPreferencesManager.getNotificationTimeMinute()
        val isShowLensPowerSection = sharedPreferencesManager.getIsShowContactLensPowerSection()
        val leftLensPower =
            sharedPreferencesManager.getLeftContactLensPower()?.toDouble() ?: -4.00
        val rightLensPower =
            sharedPreferencesManager.getRightContactLensPower()?.toDouble() ?: -4.00

        return SettingValue(
            lensType = lensType,
            lensPeriod = lensPeriod,
            isUseNotification = isUseNotification,
            notificationDay = notificationDay,
            notificationTimeHour = notificationTimeHour,
            notificationTimeMinute = notificationTimeMinute,
            isShowLensPowerSection = isShowLensPowerSection,
            leftLensPower = leftLensPower,
            rightLensPower = rightLensPower
        )
    }
}