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