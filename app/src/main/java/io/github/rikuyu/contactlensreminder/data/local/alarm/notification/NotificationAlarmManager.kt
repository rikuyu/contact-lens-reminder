package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import io.github.rikuyu.contactlensreminder.data.util.createBroadcastPendingIntent
import io.github.rikuyu.contactlensreminder.data.util.getDateChangeTime
import javax.inject.Inject

class NotificationAlarmManager @Inject constructor(private val context: Context) {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        alarmManager.setExact(
            AlarmManager.RTC,
            getDateChangeTime(),
            createBroadcastPendingIntent(context, NotificationAlarmReceiver::class.java, REQUEST_CODE)
        )
    }

    fun cancelAlarm() {
        alarmManager.cancel(createBroadcastPendingIntent(context, NotificationAlarmReceiver::class.java, REQUEST_CODE))
    }

    companion object {
        private const val REQUEST_CODE = 55555
    }
}