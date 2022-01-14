package com.example.contactlensreminder.data.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesKey.SHARED_PPREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getContactLensType(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_TYPE,
            0
        )
    }

    fun saveContactLensType(lensType: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_TYPE, lensType).apply()
    }

    fun getContactLensPeriod(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD,
            14
        )
    }

    fun saveContactLensPeriod(period: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD, period).apply()
    }

    fun getIsUsingContactLens(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_USING_CONTACT_LENS,
            false
        )
    }

    fun saveIsUsingContactLens(isUsingContactLens: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_USING_CONTACT_LENS, isUsingContactLens)
            .apply()
    }

    fun getContactLensElapsedDays(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_ELAPSED_DAYS,
            14
        )
    }

    fun saveContactLensElapsedDays(elapsedDays: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_ELAPSED_DAYS, elapsedDays).apply()
    }

    fun getIsUseNotification(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_USE_NOTIFICATION,
            true
        )
    }

    fun saveIsUseNotification(isUseNotification: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_USE_NOTIFICATION, isUseNotification).apply()
    }

    fun getNotificationDay(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_DAY,
            0
        )
    }

    fun saveNotificationDay(notificationDay: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_DAY, notificationDay).apply()
    }

    fun getNotificationTimeHour(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_TIME_HOUR,
            9
        )
    }

    fun saveNotificationTimeHour(notificationTime: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_TIME_HOUR, notificationTime).apply()
    }

    fun getNotificationTimeMinute(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_TIME_MINUTE,
            15
        )
    }

    fun saveNotificationTimeMinute(notificationTime: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_TIME_MINUTE, notificationTime)
            .apply()
    }

    fun getLeftContactLensPower(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveLeftContactLensPower(lensPower: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER, lensPower).apply()
    }

    fun getRightContactLensPower(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveRightContactLensPower(lensPower: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER, lensPower).apply()
    }
}