package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.createBroadcastPendingIntent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NotificationAlarmManager @Inject constructor(
    private val context: Context,
    private val sharedPreferencesManager: SharedPreferencesManager,
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.ENGLISH)
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
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, null),
            createBroadcastPendingIntent(
                context,
                NotificationAlarmReceiver::class.java,
                REQUEST_CODE
            )
        )
    }

    fun cancelAlarm() {
        alarmManager.cancel(createBroadcastPendingIntent(context, NotificationAlarmReceiver::class.java, REQUEST_CODE))
    }

    companion object {
        private const val REQUEST_CODE = 55555
    }
}