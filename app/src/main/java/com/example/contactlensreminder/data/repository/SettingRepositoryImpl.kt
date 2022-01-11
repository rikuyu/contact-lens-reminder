package com.example.contactlensreminder.data.repository

import android.content.Context
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.presentation.screens.lens_setting.SettingValue

class SettingRepositoryImpl(private val context: Context) : SettingRepository {

    private val sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager()

    override fun saveAllSetting(settingValue: SettingValue) {
        val lensType = settingValue.lensType
        val lensPeriod = settingValue.lensPeriod
        val isUseNotification = settingValue.isUseNotification
        val notificationDay = settingValue.notificationDay
        val notificationTime = settingValue.notificationTime
        val leftLensPower = settingValue.leftLensPower
        val rightLensPower = settingValue.rightLensPower

        sharedPreferencesManager.apply {
            saveContactLensType(context, lensType)
            saveContactLensPeriod(context, lensPeriod)
            saveIsUseNotification(context, isUseNotification)
            saveNotificationDay(context, notificationDay)
            saveNotificationTime(context, notificationTime)
            saveLeftContactLensPower(context, leftLensPower.toString())
            saveRightContactLensPower(context, rightLensPower.toString())
        }
    }

    override fun getAllSetting(): SettingValue {
        val lensType = sharedPreferencesManager.getContactLensType(context)
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod(context)
        val isUseNotification = sharedPreferencesManager.getIsUseNotification(context)
        val notificationDay = sharedPreferencesManager.getNotificationDay(context)
        val notificationTime = sharedPreferencesManager.getNotificationTime(context) ?: "7:00"
        val leftLensPower =
            sharedPreferencesManager.getLeftContactLensPower(context)?.toDouble() ?: -4.00
        val rightLensPower =
            sharedPreferencesManager.getRightContactLensPower(context)?.toDouble() ?: -4.00

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