package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import io.github.rikuyu.contactlensreminder.data.util.createIntent
import io.github.rikuyu.contactlensreminder.data.util.getDateChangeTime
import io.github.rikuyu.contactlensreminder.ui.appwidget.ProgressBarTypeWidget
import javax.inject.Inject

class TickDownAlarmManager @Inject constructor(
    private val context: Context,
    private val firebaseLogEvent: FirebaseLogEvent
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val intent = Intent(context, TickDownAlarmReceiver::class.java)
        alarmManager.setExact(
            AlarmManager.RTC,
            getDateChangeTime(),
            createIntent(context, intent, SHARED_PREFERENCE_DATA_CODE)
        )
        firebaseLogEvent.logInitTickDownEvent()
    }

    fun cancelAlarm() {
        val intent = Intent(context, TickDownAlarmReceiver::class.java)
        alarmManager.cancel(createIntent(context, intent, SHARED_PREFERENCE_DATA_CODE))
        firebaseLogEvent.logEvent("cancel_tick_down_alarm_event")
    }

    companion object {
        private const val SHARED_PREFERENCE_DATA_CODE = 777777
    }
}