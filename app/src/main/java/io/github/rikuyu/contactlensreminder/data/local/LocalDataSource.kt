package io.github.rikuyu.contactlensreminder.data.local

import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val tickDownAlarmManager: TickDownAlarmManager,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val notificationAlarmManager: NotificationAlarmManager,
    private val changeAppIconService: ChangeAppIconService
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

    override fun startReminder() {
        sharedPreferencesManager.apply {
            if (getIsUseNotification()) {
                notificationAlarmManager.initAlarm()
            }
            changeAppIconService.changeAppIcon(
                true, getContactLensRemainingDays()
            )
            tickDownAlarmManager.initAlarm()
            if (!getIsFirstUse()) {
                saveIsFirstUse()
                saveUuid(UUID.randomUUID().toString())
            }
        }
    }

    override fun getReminderSetting(): ReminderValue {
        sharedPreferencesManager.apply {
            val lensPeriod = getContactLensPeriod()
            val notificationDay = getNotificationDay()
            val notificationTimeHour = getNotificationTimeHour()
            val notificationTimeMinute = getNotificationTimeMinute()
            val lensRemainingDays = getContactLensRemainingDays()
            val isUsingContactLens = getIsUsingContactLens()
            val isUseNotification = getIsUseNotification()
            val exchangeDay = getLensExchangeDay() ?: getExpirationDate(lensPeriod)

            return ReminderValue(
                lensPeriod = lensPeriod,
                exchangeDay = exchangeDay,
                notificationDay = notificationDay,
                notificationTimeHour = notificationTimeHour,
                notificationTimeMinute = notificationTimeMinute,
                lensRemainingDays = lensRemainingDays,
                isUsingContactLens = isUsingContactLens,
                isUseNotification = isUseNotification
            )
        }
    }

    override fun cancelReminder() {
        if (sharedPreferencesManager.getIsUseNotification()) {
            notificationAlarmManager.cancelAlarm()
        }
        tickDownAlarmManager.cancelAlarm()
    }

    override fun saveAllSetting(settingValue: SettingValue) {
        val remainingRay = settingValue.lensPeriod

        sharedPreferencesManager.apply {
            saveContactLensType(settingValue.lensType)
            saveContactLensPeriod(settingValue.lensPeriod)
            saveIsUseNotification(settingValue.isUseNotification)
            saveNotificationDay(settingValue.notificationDay)
            saveNotificationTimeHour(settingValue.notificationTimeHour)
            saveNotificationTimeMinute(settingValue.notificationTimeMinute)
            saveIsShowContactLensPowerSection(settingValue.isShowLensPowerSection)
            saveLeftContactLensPower(settingValue.leftLensPower)
            saveRightContactLensPower(settingValue.rightLensPower)
            saveContactLensRemainingDays(remainingRay)
            saveLensExchangeDay(getExpirationDate(remainingRay))
        }
    }

    override fun getAllSetting(): SettingValue {
        sharedPreferencesManager.apply {
            val lensType = getContactLensType()
            val lensPeriod = getContactLensPeriod()
            val isUseNotification = getIsUseNotification()
            val notificationDay = getNotificationDay()
            val notificationTimeHour = getNotificationTimeHour()
            val notificationTimeMinute = getNotificationTimeMinute()
            val isShowLensPowerSection = getIsShowContactLensPowerSection()
            val leftLensPower = getLeftContactLensPower() ?: "-4.00"
            val rightLensPower = getRightContactLensPower() ?: "-4.00"

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
}