package io.github.rikuyu.contactlensreminder.data.local

import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val tickDownAlarmManager: TickDownAlarmManager,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val notificationAlarmManager: NotificationAlarmManager,
    private val changeAppIconService: ChangeAppIconService,
    private val firebaseLogEvent: FirebaseLogEvent
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
//            changeAppIconService.changeAppIcon(
//                true, getContactLensRemainingDays()
//            )
            tickDownAlarmManager.initAlarm()
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
            saveLensExchangeDay(getExpirationDate(remainingRay))

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
        firebaseLogEvent.logEvent(label)
    }
}