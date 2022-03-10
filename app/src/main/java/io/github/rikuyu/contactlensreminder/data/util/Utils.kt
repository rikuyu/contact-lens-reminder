package io.github.rikuyu.contactlensreminder.data.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, lensPeriod)
    val simpleDateFormat = SimpleDateFormat("MM/dd/E", Locale.getDefault())
    val (m, d, day) = simpleDateFormat.format(calendar.time).split("/")
    return "${m.toInt()}/${d.toInt()} ($day)"
}

fun <T> createBroadcastPendingIntent(
    context: Context,
    klass: Class<T>,
    requestCode: Int,
    action: String? = null
): PendingIntent {
    val intent = Intent(context, klass)
    action?.let { intent.action = it }
    return PendingIntent.getBroadcast(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
}