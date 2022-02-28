package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent

class TickDownAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPreferencesManager = SharedPreferencesManager(it)
            val firebaseLogEvent = FirebaseLogEvent(sharedPreferencesManager)
            val tickDownAlarmManager = TickDownAlarmManager(it, firebaseLogEvent)
            val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
            if (remainingDay > 0) {
                val after = remainingDay - 1
                sharedPreferencesManager.saveContactLensRemainingDays(after)
                if (after > 0) {
                    tickDownAlarmManager.initAlarm()
                    firebaseLogEvent.logEvent("receive_tick_down_alarm_event")
                }
            }
        }
    }
}