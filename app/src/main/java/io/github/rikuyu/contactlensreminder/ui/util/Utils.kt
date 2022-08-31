package io.github.rikuyu.contactlensreminder.ui.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.rikuyu.contactlensreminder.R

fun makeNotificationSettingIntent(context: Context): Intent {
    return Intent().apply {
        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
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

fun showAlertDialog(
    context: Context,
    @DrawableRes icon: Int = R.drawable.icon_default,
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes positiveButtonLabel: Int,
    event: () -> Unit,
) {
    val dialog = AlertDialog.Builder(context)
        .setIcon(icon)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonLabel) { _, _ ->
            event.invoke()
        }
        .setCancelable(false)
        .create()
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()
}