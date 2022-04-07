package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.ui.MainActivity

class ProgressBarTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        context.applicationContext.registerReceiver(
            this@ProgressBarTypeWidget,
            IntentFilter(Intent.ACTION_USER_PRESENT)
        )

        for (id in appWidgetIds) {
            updateProgressBarTypeWidget(context, appWidgetManager, id)
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
                updateProgressBarTypeWidget(context, appWidgetManager, id)
            }
        }
    }

    fun updateProgressBarTypeWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
    ) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val exchangeDate = sharedPreferencesManager.getLensExchangeDay() ?: getExpirationDate(lensPeriod)
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CODE_ACTIVITY,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val view = RemoteViews(context.packageName, R.layout.widget_progress_bar_type)
        view.apply {
            setTextViewText(R.id.tv_exchange_date, exchangeDate)
            setOnClickPendingIntent(
                R.id.widget_progress_bar_type,
                pendingIntent
            )
            setViewVisibility(R.id.tv_remaining_day_before, View.VISIBLE)
            setTextViewText(R.id.tv_remaining_day, remainingDay.toString())
            setViewVisibility(R.id.tv_remaining_day_after, View.VISIBLE)

            if (remainingDay > 0) {
                setViewVisibility(R.id.progress_bar, View.VISIBLE)
                setViewVisibility(R.id.progress_bar_expired, View.GONE)
                setProgressBar(
                    R.id.progress_bar,
                    100,
                    if (isUsingContactLens)
                        (100.0 / lensPeriod * remainingDay).toInt()
                    else 100,
                    false
                )
            } else {
                setViewVisibility(R.id.progress_bar, View.GONE)
                setViewVisibility(R.id.progress_bar_expired, View.VISIBLE)
            }
        }

        appWidgetManager.updateAppWidget(appWidgetId, view)
    }

    companion object {
        private const val ACTION_CODE = "PROGRESS_BAR_TYPE_WIDGET_TICK_DOWN"
        private const val REQUEST_CODE_ACTIVITY = 666666
    }
}