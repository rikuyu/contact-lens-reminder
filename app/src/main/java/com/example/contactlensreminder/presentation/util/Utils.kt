package com.example.contactlensreminder.presentation.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calender = Calendar.getInstance()
    calender.time = Date()
    calender.add(Calendar.DATE, lensPeriod)
    val date: Date = calender.time
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    return simpleDateFormat.format(date)
}

fun makeNotificationSettingIntent(context: Context): Intent {
    return Intent().apply {
        action = android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
        putExtra("app_package", context.packageName)
        putExtra("app_uid", context.applicationInfo.uid)
        putExtra("android.provider.extra.APP_PACKAGE", context.packageName)
    }
}

fun checkNetworkConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}