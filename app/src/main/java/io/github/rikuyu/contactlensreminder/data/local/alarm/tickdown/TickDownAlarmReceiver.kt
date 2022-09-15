package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.ui.appwidget.ImageTypeWidget
import io.github.rikuyu.contactlensreminder.ui.appwidget.ProgressBarTypeWidget

class TickDownAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPreferencesService = SharedPreferencesService(it)
            val isUsingContactLens = sharedPreferencesService.getIsUsingContactLens()
            val tickDownAlarmService = TickDownAlarmService(it)
            val remainingDay = sharedPreferencesService.getContactLensRemainingDays()

            // 端末電源OFFにした時の対策
            // 電源がONになったときにイベントを再登録する
            if (isUsingContactLens) {
                if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
                    FirebaseLogEventService(sharedPreferencesService).logEvent("RECEIVE_BOOT_COMPLETED_TICKDOWN")
                    // ※ 電源OFFの状態で日付をまたぐとバグる実装
                    if (remainingDay > 0) {
                        tickDownAlarmService.initAlarm()
                        updateAppWidget(it)
                    }
                } else {
                    if (remainingDay > 0) {
                        val after = remainingDay - 1
                        sharedPreferencesService.saveContactLensRemainingDays(after)
                        if (after > 0) {
                            tickDownAlarmService.initAlarm()
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