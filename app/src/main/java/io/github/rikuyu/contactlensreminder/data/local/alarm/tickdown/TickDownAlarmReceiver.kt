package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import javax.inject.Inject

class TickDownAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPreferencesManager = SharedPreferencesManager(it)
            val changeAppIconService = ChangeAppIconService(it)
            val tickDownAlarmManager = TickDownAlarmManager(it)

            val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
            val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
            if (remainingDay > 0) {
                val after = remainingDay - 1
                sharedPreferencesManager.saveContactLensRemainingDays(after)
                changeAppIconService.changeAppIcon(isUsingContactLens, after)
                if (after > 0) {
                    tickDownAlarmManager.initAlarm()
                }
            }
        }
    }
}