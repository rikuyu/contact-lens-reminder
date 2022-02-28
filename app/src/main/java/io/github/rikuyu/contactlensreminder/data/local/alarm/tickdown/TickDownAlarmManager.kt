package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.content.Context
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import io.github.rikuyu.contactlensreminder.data.util.createBroadcastPendingIntent
import io.github.rikuyu.contactlensreminder.data.util.getDateChangeTime
import javax.inject.Inject

class TickDownAlarmManager @Inject constructor(
    private val context: Context,
    private val firebaseLogEvent: FirebaseLogEvent
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        alarmManager.setExact(
            AlarmManager.RTC,
            getDateChangeTime(),
            createBroadcastPendingIntent(context, TickDownAlarmReceiver::class.java, SHARED_PREFERENCE_DATA_CODE)
        )
        firebaseLogEvent.logInitTickDownEvent()
    }

    fun cancelAlarm() {
        alarmManager.cancel(
            createBroadcastPendingIntent(
                context,
                TickDownAlarmReceiver::class.java,
                SHARED_PREFERENCE_DATA_CODE
            )
        )
        firebaseLogEvent.logEvent("cancel_tick_down_alarm_event")
    }

    companion object {
        private const val SHARED_PREFERENCE_DATA_CODE = 777777
    }
}