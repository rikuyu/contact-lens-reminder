package com.example.contactlensreminder.presentation.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesKey.SHARED_PPREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getContactLensType(context: Context): String? {
        return getSharedPreferences(context).getString(
            SharedPreferencesKey.STORED_CONTACT_LENS_TYPE,
            null
        )
    }

    fun saveContactLensType(context: Context, lensType: String) {
        getSharedPreferences(context).edit()
            .putString(SharedPreferencesKey.STORED_CONTACT_LENS_TYPE, lensType).apply()
    }

    fun getContactLensPeriod(context: Context): Int {
        return getSharedPreferences(context).getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD,
            1
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

    fun getNotificationDay(context: Context): String? {
        return getSharedPreferences(context).getString(
            SharedPreferencesKey.STORED_NOTIFY_DAY,
            null
        )
    }

    fun saveNotificationDay(context: Context, notificationDay: String) {
        getSharedPreferences(context).edit()
            .putString(SharedPreferencesKey.STORED_NOTIFY_DAY, notificationDay).apply()
    }
}