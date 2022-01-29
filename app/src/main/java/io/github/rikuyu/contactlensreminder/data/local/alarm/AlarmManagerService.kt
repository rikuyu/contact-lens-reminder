package io.github.rikuyu.contactlensreminder.data.local.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
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
        val simpleDateFormat = SimpleDateFormat("hh/mm/ss", Locale.ENGLISH)
        val (hour, min, sec) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            timeInMillis = System.currentTimeMillis()
            add(
                Calendar.DAY_OF_MONTH,
                sharedPreferencesManager.getContactLensPeriod() - sharedPreferencesManager.getNotificationDay()
            )
            add(Calendar.HOUR, sharedPreferencesManager.getNotificationTimeHour() - hour)
            add(Calendar.MINUTE, sharedPreferencesManager.getNotificationTimeMinute() - min)
            add(Calendar.SECOND, -sec)
        }
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