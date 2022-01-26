package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.sharedpreferences.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.domain.SettingValue

class SettingRepositoryImpl(private val sharedPreferencesManager: SharedPreferencesManager) :
    SettingRepository {

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
            saveContactLensElapsedDays(elapsedDays)
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