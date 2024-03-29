package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
import io.github.rikuyu.contactlensreminder.ui.MainActivity
import javax.inject.Inject

class NotificationService @Inject constructor(val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    private val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    private val notificationDay = SharedPreferencesService(context).getNotificationDay()

    private val pendingIntent: PendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

    private var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_water_drop)
        .setLargeIcon(
            BitmapFactory.decodeResource(context.resources, R.drawable.ic_water_drop)
        )
        .setColor(ContextCompat.getColor(context, R.color.light_blue))
        .setContentTitle(
            context.getString(
                if (notificationDay == 1) {
                    R.string.notification_before_day_title
                } else {
                    R.string.notification_on_the_day_title
                }
            )
        )
        .setContentText(context.getString(R.string.notification_content))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setVisibility(VISIBILITY_PUBLIC)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    // Build.VERSION.SDK_INT >= Build.VERSION_CODES.O always true. min SDK 26
    fun createChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.notification_setting_label),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification() {
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        private const val CHANNEL_ID = "contact_lens_reminder_channel_id"
        private const val NOTIFICATION_ID = 111111
        private const val CHANNEL_DESCRIPTION = "contact_lens_reminder_channel_description "
    }
}