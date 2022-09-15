package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService

class NotificationAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPreferencesService = SharedPreferencesService(it)
            val isUsingContactLens = sharedPreferencesService.getIsUsingContactLens()

            // 端末電源OFFにした時の対策
            // 電源がONになったときにイベントを再登録する
            if (isUsingContactLens) {
                if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
                    FirebaseLogEventService(SharedPreferencesService(it))
                        .logEvent("RECEIVE_BOOT_COMPLETED_NOTIFICATION")
                    // ※ 電源OFFの状態で日付をまたぐとバグる実装
                    if (!sharedPreferencesService.getIsExecuteNotification() &&
                        sharedPreferencesService.getIsUseNotification()
                    ) {
                        NotificationAlarmService(it, sharedPreferencesService).initAlarm()
                    }
                } else {
                    NotificationService(it).showNotification()
                    sharedPreferencesService.saveIsExecuteNotification(true)
                }
            }
        }
    }
}