package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService

class NotificationAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            NotificationService(it).showNotification()

            // 日付をまたぐ際に端末電源OFFだった時の対策
            if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
                FirebaseLogEventService(SharedPreferencesManager(it))
                    .logEvent("RECEIVE_BOOT_COMPLETED_NOTIFICATION")
                NotificationService(it).showNotification()
            }
        }
    }
}