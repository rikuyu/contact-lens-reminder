package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import io.github.rikuyu.contactlensreminder.data.util.createBroadcastPendingIntent
import io.github.rikuyu.contactlensreminder.data.util.getDateChangeTime
import io.github.rikuyu.contactlensreminder.ui.appwidget.ImageTypeWidget
import io.github.rikuyu.contactlensreminder.ui.appwidget.ProgressBarTypeWidget
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
        updateAppWidget(context)
    }

    private fun updateAppWidget(context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        ProgressBarTypeWidget().apply {
            cancelUpdateAppWidget(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context.packageName, javaClass.name))
            for (id in appWidgetIds) {
                updateProgressBarTypeWidget(context, appWidgetManager, id)
            }
        }
        ImageTypeWidget().apply {
            cancelUpdateAppWidget(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context.packageName, javaClass.name))
            for (id in appWidgetIds) {
                updateImageTypeWidget(context, appWidgetManager, id)
            }
        }
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