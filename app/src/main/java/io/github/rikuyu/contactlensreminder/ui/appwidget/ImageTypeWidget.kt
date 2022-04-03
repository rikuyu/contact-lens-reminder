package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.ui.MainActivity
import java.util.*

class ImageTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        context.applicationContext.registerReceiver(this, IntentFilter(Intent.ACTION_USER_PRESENT))

        for (id in appWidgetIds) {
            updateImageTypeWidget(context, appWidgetManager, id)
        }
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
    }

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)

        val action = intent?.action ?: return
        if (action == ACTION_CODE) {
            val appWidget = ComponentName(context.packageName, javaClass.name)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(appWidget)
            for (id in appWidgetIds) {
                updateImageTypeWidget(context, appWidgetManager, id)
            }
        }
    }

    fun updateImageTypeWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
    ) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CODE_ACTIVITY,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val view = RemoteViews(context.packageName, R.layout.widget_image_type)
        view.apply {
            setImageViewResource(R.id.widget_image, getRemainingDayDrawable(remainingDay))
            setOnClickPendingIntent(R.id.widget_image_type, pendingIntent)
        }
        appWidgetManager.updateAppWidget(appWidgetId, view)
    }

    private fun getRemainingDayDrawable(remainingDay: Int): Int {
        @DrawableRes
        val widgetImageResId = when (remainingDay) {
            0 -> R.drawable.icon_expired
            1 -> R.drawable.icon_1
            2 -> R.drawable.icon_2
            3 -> R.drawable.icon_3
            4 -> R.drawable.icon_4
            5 -> R.drawable.icon_5
            6 -> R.drawable.icon_6
            7 -> R.drawable.icon_7
            8 -> R.drawable.icon_8
            9 -> R.drawable.icon_9
            10 -> R.drawable.icon_10
            11 -> R.drawable.icon_11
            12 -> R.drawable.icon_12
            13 -> R.drawable.icon_13
            14 -> R.drawable.icon_14
            15 -> R.drawable.icon_15
            16 -> R.drawable.icon_16
            17 -> R.drawable.icon_17
            18 -> R.drawable.icon_18
            19 -> R.drawable.icon_19
            20 -> R.drawable.icon_20
            21 -> R.drawable.icon_21
            22 -> R.drawable.icon_22
            23 -> R.drawable.icon_23
            24 -> R.drawable.icon_24
            25 -> R.drawable.icon_25
            26 -> R.drawable.icon_26
            27 -> R.drawable.icon_27
            28 -> R.drawable.icon_28
            29 -> R.drawable.icon_29
            30 -> R.drawable.icon_30
            31 -> R.drawable.icon_31
            else -> R.drawable.icon_default
        }
        return widgetImageResId
    }

    companion object {
        const val ACTION_CODE = "IMAGE_TYPE_WIDGET_UPDATE"
        private const val REQUEST_CODE_ACTIVITY = 888888
    }
}