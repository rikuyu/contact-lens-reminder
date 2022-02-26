package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TickDownAlarmManager @Inject constructor(private val context: Context) {

    private val changeAppIconService: ChangeAppIconService = ChangeAppIconService(context)
    private val sharedPreferencesManager = SharedPreferencesManager(context)
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun initAlarm() {
        val intent = Intent(context, TickDownAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
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
            pendingIntent
        )
        logInitTickDownEvent()
    }

    fun cancelAlarm() {
        val intent = Intent(context, TickDownAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
        changeAppIconService.changeAppIcon(false, null)
        logCancelTickDownEvent()
    }

    private fun logInitTickDownEvent() {
        val uuid = sharedPreferencesManager.getUuid() ?: return
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod().toString()
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays().toString()

        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("init_tick_down_alarm_event") {
            param(UUID, uuid)
            param(LENS_PERIOD, lensPeriod)
            param(REMAINING_DAY, remainingDay)
        }
    }

    private fun logCancelTickDownEvent() {
        val uuid = sharedPreferencesManager.getUuid() ?: return
        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("cancel_tick_down_alarm_event") { param(UUID, uuid) }
    }

    companion object {
        private const val REQUEST_CODE = 7777
        private const val LENS_PERIOD = "lens_period"
        private const val REMAINING_DAY = "remaining_day"
        private const val UUID = "uuid"
    }
}