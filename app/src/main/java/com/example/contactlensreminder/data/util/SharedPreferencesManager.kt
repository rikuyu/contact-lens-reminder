package com.example.contactlensreminder.data.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesKey.SHARED_PPREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getContactLensType(context: Context): Int {
        return getSharedPreferences(context).getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_TYPE,
            0
        )
    }

    fun saveContactLensType(context: Context, lensType: Int) {
        getSharedPreferences(context).edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_TYPE, lensType).apply()
    }

    fun getContactLensPeriod(context: Context): Int {
        return getSharedPreferences(context).getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD,
            14
        )
    }

    fun saveContactLensPeriod(context: Context, period: Int) {
        getSharedPreferences(context).edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD, period).apply()
    }

    fun getIsUseNotification(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(
            SharedPreferencesKey.STORED_IS_USE_NOTIFICATION,
            true
        )
    }

    fun saveIsUseNotification(context: Context, isUseNotification: Boolean) {
        getSharedPreferences(context).edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_USE_NOTIFICATION, isUseNotification).apply()
    }

    fun getNotificationDay(context: Context): Int {
        return getSharedPreferences(context).getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_DAY,
            0
        )
    }

    fun saveNotificationDay(context: Context, notificationDay: Int) {
        getSharedPreferences(context).edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_DAY, notificationDay).apply()
    }

    fun getNotificationTime(context: Context): String? {
        return getSharedPreferences(context).getString(
            SharedPreferencesKey.STORED_NOTIFICATION_TIME,
            null
        )
    }

    fun saveNotificationTime(context: Context, notificationTime: String) {
        getSharedPreferences(context).edit()
            .putString(SharedPreferencesKey.STORED_NOTIFICATION_TIME, notificationTime).apply()
    }

    fun getLeftContactLensPower(context: Context): String? {
        return getSharedPreferences(context).getString(
            SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveLeftContactLensPower(context: Context, lensPower: String) {
        getSharedPreferences(context).edit()
            .putString(SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER, lensPower).apply()
    }

    fun getRightContactLensPower(context: Context): String? {
        return getSharedPreferences(context).getString(
            SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveRightContactLensPower(context: Context, lensPower: String) {
        getSharedPreferences(context).edit()
            .putString(SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER, lensPower).apply()
    }
}