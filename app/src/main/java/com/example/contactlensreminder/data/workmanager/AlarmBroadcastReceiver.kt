package com.example.contactlensreminder.data.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationService = NotificationService(it)
            notificationService.showNotification()
        }
    }
}