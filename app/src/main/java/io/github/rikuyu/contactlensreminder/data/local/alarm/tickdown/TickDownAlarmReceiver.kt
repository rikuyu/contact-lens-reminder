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
            val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
            val tickDownAlarmManager = TickDownAlarmManager(it)
            val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()

            // 端末電源OFFにした時の対策
            // 電源がONになったときにイベントを再登録する
            if (isUsingContactLens) {
                if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
                    FirebaseLogEventService(sharedPreferencesManager).logEvent("RECEIVE_BOOT_COMPLETED_TICKDOWN")
                    // ※ 電源OFFの状態で日付をまたぐとバグる実装
                    if (remainingDay > 0) {
                        tickDownAlarmManager.initAlarm()
                        updateAppWidget(it)
                    }
                } else {
                    if (remainingDay > 0) {
                        val after = remainingDay - 1
                        sharedPreferencesManager.saveContactLensRemainingDays(after)
                        if (after > 0) {
                            tickDownAlarmManager.initAlarm()
                        }
                        updateAppWidget(it)
                    } else {
                        // NOP
                    }
                }
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