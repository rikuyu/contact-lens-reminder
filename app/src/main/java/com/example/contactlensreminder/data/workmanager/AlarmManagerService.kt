package com.example.contactlensreminder.data.workmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import java.text.SimpleDateFormat
import java.util.*

class AlarmManagerService(
    private val context: Context,
    private val sharedPreferencesManager: SharedPreferencesManager
) {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val calendar = Calendar.getInstance()
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val simpleDateFormat = SimpleDateFormat("hh/mm", Locale.ENGLISH)
        val (hour, min) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            add(Calendar.DATE, sharedPreferencesManager.getContactLensPeriod())
            add(Calendar.DATE, sharedPreferencesManager.getNotificationDay())
            add(Calendar.HOUR, -hour)
            add(Calendar.MINUTE, -min)
            add(Calendar.HOUR, sharedPreferencesManager.getNotificationTimeHour())
            add(Calendar.MINUTE, sharedPreferencesManager.getNotificationTimeMinute())
        }
        calendar.add(Calendar.SECOND, 10)
        alarmManager.setExact(
            AlarmManager.RTC,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    fun cancelAlarm() {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}