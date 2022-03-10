package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import io.github.rikuyu.contactlensreminder.data.util.createBroadcastPendingIntent
import io.github.rikuyu.contactlensreminder.ui.appwidget.ImageTypeWidget
import io.github.rikuyu.contactlensreminder.ui.appwidget.ProgressBarTypeWidget
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TickDownAlarmManager @Inject constructor(
    private val context: Context,
    private val firebaseLogEvent: FirebaseLogEvent
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.ENGLISH)
        val (hour, min, sec) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.HOUR, 24 - hour)
            add(Calendar.MINUTE, -min)
            add(Calendar.SECOND, -sec)
        }
        alarmManager.setExact(
            AlarmManager.RTC,
            calendar.timeInMillis,
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
        updateAppWidget(context)
        firebaseLogEvent.logEvent("cancel_tick_down_alarm_event")
    }

    companion object {
        private const val SHARED_PREFERENCE_DATA_CODE = 777777
    }
}