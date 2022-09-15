package io.github.rikuyu.contactlensreminder.data.local

import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmService
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationService
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmService
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val tickDownAlarmService: TickDownAlarmService,
    private val sharedPreferencesService: SharedPreferencesService,
    private val notificationAlarmService: NotificationAlarmService,
    private val firebaseLogEventService: FirebaseLogEventService,
    private val notificationService: NotificationService,
) : DataSource {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        sharedPreferencesService.apply {
            saveContactLensRemainingDays(reminderValue.lensRemainingDays)
            saveContactLensPeriod(reminderValue.lensPeriod)
            saveNotificationTimeHour(reminderValue.notificationTimeHour)
            saveNotificationTimeMinute(reminderValue.notificationTimeMinute)
            saveIsUsingContactLens(reminderValue.isUsingContactLens)
            saveLensExchangeDate(getExpirationDate(reminderValue.lensPeriod))
        }
    }

    override fun startReminder() {
        if (sharedPreferencesService.getIsUseNotification()) {
            notificationAlarmService.initAlarm()
            sharedPreferencesService.saveIsExecuteNotification(false)
        }
        tickDownAlarmService.initAlarm()
        tickDownAlarmService.updateAppWidget()
    }

    override fun getReminderSetting(): ReminderValue {
        sharedPreferencesService.apply {
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

    override fun resetReminder() {
        if (sharedPreferencesService.getIsUseNotification()) {
            notificationAlarmService.cancelAlarm()
            sharedPreferencesService.saveIsExecuteNotification(true)
        }
        tickDownAlarmService.cancelAlarm()
    }

    override fun saveAllLensSetting(lensSettingValue: LensSettingValue) {
        val remainingRay = lensSettingValue.lensPeriod

        sharedPreferencesService.apply {
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
        sharedPreferencesService.apply {
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

    override fun getIsShowOnBoarding(): Boolean = false

    override fun getIsDarkTheme(): Boolean = sharedPreferencesService.getIsDarkTheme()

    override fun saveIsDarkTheme() {
        sharedPreferencesService.saveIsDarkTheme(!getIsDarkTheme())
    }

    override fun getThemeColor(): String = sharedPreferencesService.getThemeColor()

    override fun saveThemeColor(color: String) {
        sharedPreferencesService.saveThemeColor(color)
    }

    override fun createChannel() {
        notificationService.createChannel()
    }
}