package com.example.contactlensreminder.data.repository

import android.content.Context
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.domain.repository.MainRepository
import com.example.contactlensreminder.presentation.SettingValue

class MainRepositoryImpl(private val context: Context) : MainRepository {

    private val sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager()

    override fun saveLensPower(leftLensPower: Double, rightLensPower: Double) {
        sharedPreferencesManager.apply {
            saveLeftContactLensPower(context, leftLensPower.toString())
            saveRightContactLensPower(context, rightLensPower.toString())
        }
    }

    override fun setAllSetting(settingValue: SettingValue) {
        val lensType = settingValue.lensType
        val lensPeriod = settingValue.lensPeriod
        val isUseNotification = settingValue.isUseNotification
        val notificationDay = settingValue.notificationDay
        val leftLensPower = settingValue.leftLensPower
        val rightLensPower = settingValue.rightLensPower

        sharedPreferencesManager.apply {
            saveContactLensType(context, lensType)
            saveContactLensPeriod(context, lensPeriod)
            saveIsUseNotification(context, isUseNotification)
            saveNotificationDay(context, notificationDay)
            saveLeftContactLensPower(context, leftLensPower.toString())
            saveRightContactLensPower(context, rightLensPower.toString())
        }
    }

    override fun getAllSetting(): SettingValue {
        val lensType = sharedPreferencesManager.getContactLensType(context)
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod(context)
        val isUseNotification = sharedPreferencesManager.getIsUseNotification(context)
        val notificationDay = sharedPreferencesManager.getNotificationDay(context)
        val leftLensPower =
            sharedPreferencesManager.getLeftContactLensPower(context)?.toDouble() ?: -4.00
        val rightLensPower =
            sharedPreferencesManager.getRightContactLensPower(context)?.toDouble() ?: -4.00

        return SettingValue(
            lensType = lensType,
            lensPeriod = lensPeriod,
            isUseNotification = isUseNotification,
            notificationDay = notificationDay,
            leftLensPower = leftLensPower,
            rightLensPower = rightLensPower
        )
    }

    override fun setReminder() {
        TODO("Not yet implemented")
    }
}