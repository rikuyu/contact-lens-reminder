package com.example.contactlensreminder.domain.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.contactlensreminder.R
import java.text.SimpleDateFormat
import java.util.*

class NotificationService(val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_water_drop)
        .setLargeIcon(
            BitmapFactory.decodeResource(context.resources, R.drawable.ic_water_drop)
        )
        .setContentTitle(R.string.notification.toString())
        .setContentText(R.string.terms_of_service.toString())
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setWhen(System.currentTimeMillis())

    private fun createChannel() {
        // Build.VERSION.SDK_INT >= Build.VERSION_CODES.O always true. min SDK 26
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification() {
        createChannel()
        notificationManager.notify(1, notificationBuilder.build())
    }

    companion object {
        private val CHANNEL_ID = "contact_lens_reminder_channel_id"
        private val CHANNEL_NAME = "contact_lens_reminder_channel_name"
        private val CHANNEL_DESCRIPTION = "contact_lens_reminder_channel_description "
    }
}