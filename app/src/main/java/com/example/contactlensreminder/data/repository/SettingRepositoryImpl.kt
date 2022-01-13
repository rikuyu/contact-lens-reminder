package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.presentation.screens.lens_setting.SettingValue

class SettingRepositoryImpl(private val sharedPreferencesManager: SharedPreferencesManager) :
    SettingRepository {

    override fun saveAllSetting(settingValue: SettingValue) {
        val lensType = settingValue.lensType
        val lensPeriod = settingValue.lensPeriod
        val isUseNotification = settingValue.isUseNotification
        val notificationDay = settingValue.notificationDay
        val notificationTime = settingValue.notificationTime
        val leftLensPower = settingValue.leftLensPower
        val rightLensPower = settingValue.rightLensPower

        sharedPreferencesManager.apply {
            saveContactLensType(lensType)
            saveContactLensPeriod(lensPeriod)
            saveIsUseNotification(isUseNotification)
            saveNotificationDay(notificationDay)
            saveNotificationTime(notificationTime)
            saveLeftContactLensPower(leftLensPower.toString())
            saveRightContactLensPower(rightLensPower.toString())
        }
    }

    override fun getAllSetting(): SettingValue {
        val lensType = sharedPreferencesManager.getContactLensType()
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val isUseNotification = sharedPreferencesManager.getIsUseNotification()
        val notificationDay = sharedPreferencesManager.getNotificationDay()
        val notificationTime = sharedPreferencesManager.getNotificationTime() ?: "7:00"
        val leftLensPower =
            sharedPreferencesManager.getLeftContactLensPower()?.toDouble() ?: -4.00
        val rightLensPower =
            sharedPreferencesManager.getRightContactLensPower()?.toDouble() ?: -4.00

        return SettingValue(
            lensType = lensType,
            lensPeriod = lensPeriod,
            isUseNotification = isUseNotification,
            notificationDay = notificationDay,
            notificationTime = notificationTime,
            leftLensPower = leftLensPower,
            rightLensPower = rightLensPower
        )
    }
}