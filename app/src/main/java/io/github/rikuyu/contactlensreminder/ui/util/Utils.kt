package io.github.rikuyu.contactlensreminder.ui.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.annotation.StringRes

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

fun showToast(context: Context, @StringRes label: Int) {
    Toast.makeText(
        context,
        context.getString(label),
        Toast.LENGTH_LONG
    ).show()
}

fun getJapaneseDay(day: String): String {
    return when (day) {
        "Mon" -> "月"
        "Tue" -> "火"
        "Wed" -> "水"
        "Thu" -> "木"
        "Fri" -> "金"
        "Sat" -> "土"
        "Sun" -> "日"
        else -> "月"
    }
}

fun getEnglishDay(day: String): String {
    return when (day) {
        "月" -> "Mon"
        "火" -> "Tue"
        "水" -> "Wed"
        "木" -> "Thu"
        "金" -> "Fri"
        "土" -> "Sat"
        "日" -> "Sun"
        else -> "Mon"
    }
}