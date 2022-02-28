package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.createIntent
import io.github.rikuyu.contactlensreminder.data.util.getDateChangeTime
import javax.inject.Inject

class NotificationAlarmManager @Inject constructor(
    private val context: Context,
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val intent = Intent(context, NotificationAlarmReceiver::class.java)
        alarmManager.setExact(
            AlarmManager.RTC,
            getDateChangeTime(),
            createIntent(context, intent, REQUEST_CODE)
        )
    }

    fun cancelAlarm() {
        val intent = Intent(context, NotificationAlarmReceiver::class.java)
        alarmManager.cancel(createIntent(context, intent, REQUEST_CODE))
    }

    companion object {
        private const val REQUEST_CODE = 8888
    }
}