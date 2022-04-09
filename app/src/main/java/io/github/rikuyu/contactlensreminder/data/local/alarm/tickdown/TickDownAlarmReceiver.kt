package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.ui.appwidget.ImageTypeWidget
import io.github.rikuyu.contactlensreminder.ui.appwidget.ProgressBarTypeWidget

class TickDownAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPreferencesManager = SharedPreferencesManager(it)
            val tickDownAlarmManager = TickDownAlarmManager(it)
            val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
            if (remainingDay > 0) {
                val after = remainingDay - 1
                sharedPreferencesManager.saveContactLensRemainingDays(after)
                if (after > 0) {
                    tickDownAlarmManager.initAlarm()
                }
                updateAppWidget(it)
            }
            // 端末電源OFFにした時の対策
            // 電源がONになったときにイベントを再登録する
            if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
                FirebaseLogEventService(sharedPreferencesManager).logEvent("RECEIVE_BOOT_COMPLETED_TICKDOWN")
                val after = remainingDay - 1
                sharedPreferencesManager.saveContactLensRemainingDays(after)
                if (after > 0) {
                    tickDownAlarmManager.initAlarm()
                }
                updateAppWidget(it)
            }
        }
    }

    private fun updateAppWidget(context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        ProgressBarTypeWidget().apply {
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context.packageName, javaClass.name))
            for (id in appWidgetIds) {
                updateProgressBarTypeWidget(context, appWidgetManager, id)
            }
        }
        ImageTypeWidget().apply {
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context.packageName, javaClass.name))
            for (id in appWidgetIds) {
                updateImageTypeWidget(context, appWidgetManager, id)
            }
        }
    }
}