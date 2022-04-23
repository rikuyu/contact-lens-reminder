package io.github.rikuyu.contactlensreminder.data.local

import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val tickDownAlarmManager: TickDownAlarmManager,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val notificationAlarmManager: NotificationAlarmManager,
    private val firebaseLogEventService: FirebaseLogEventService,
) : DataSource {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        sharedPreferencesManager.apply {
            saveContactLensRemainingDays(reminderValue.lensRemainingDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
            saveLensExchangeDate(getExpirationDate(reminderValue.lensPeriod))
        }
    }

    override fun startReminder() {
        if (sharedPreferencesManager.getIsUseNotification()) {
            notificationAlarmManager.initAlarm()
        }
        tickDownAlarmManager.initAlarm()
        tickDownAlarmManager.updateAppWidget()
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
            val exchangeDay = getLensExchangeDate() ?: getExpirationDate(lensPeriod)

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

    override fun saveAllLensSetting(lensSettingValue: LensSettingValue) {
        val remainingRay = lensSettingValue.lensPeriod

        sharedPreferencesManager.apply {
            saveContactLensType(lensSettingValue.lensType)
            saveContactLensPeriod(lensSettingValue.lensPeriod)
            saveIsUseNotification(lensSettingValue.isUseNotification)
            saveNotificationDay(lensSettingValue.notificationDay)
            saveNotificationTimeHour(lensSettingValue.notificationTimeHour)
            saveNotificationTimeMinute(lensSettingValue.notificationTimeMinute)
            saveIsShowContactLensPowerSection(lensSettingValue.isShowLensPowerSection)
            saveLeftContactLensPower(lensSettingValue.leftLensPower)
            saveRightContactLensPower(lensSettingValue.rightLensPower)
            saveContactLensRemainingDays(remainingRay)
            saveLensExchangeDate(getExpirationDate(remainingRay))

            if (getIsFirstUse()) {
                saveIsFirstUse()
                saveUuid(UUID.randomUUID().toString())
            }
        }
    }

    override fun getAllLensSetting(): LensSettingValue {
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

            return LensSettingValue(
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

    override fun logEvent(label: String) {
        firebaseLogEventService.logEvent(label)
    }

    override fun getIsShowOnBoarding(): Boolean {
        val isShowOnBoarding = sharedPreferencesManager.getIsShowOnBoarding()
        if (isShowOnBoarding) {
            sharedPreferencesManager.saveIsShowOnBoarding()
        }
        return isShowOnBoarding
    }

    override fun getIsDarkTheme(): Boolean = sharedPreferencesManager.getIsDarkTheme()

    override fun saveIsDarkTheme() {
        sharedPreferencesManager.saveIsDarkTheme(!getIsDarkTheme())
    }

    override fun getThemeColor(): String = sharedPreferencesManager.getThemeColor()

    override fun saveThemeColor(color: String) {
        sharedPreferencesManager.saveThemeColor(color)
    }
}