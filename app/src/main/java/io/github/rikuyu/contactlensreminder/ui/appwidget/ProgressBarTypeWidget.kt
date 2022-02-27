package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.ui.MainActivity

class ProgressBarTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateProgressBarTypeWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateProgressBarTypeWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val exchangeDate = sharedPreferencesManager.getLensExchangeDay() ?: ""
        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
            .let { intent ->
                PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        val section = 100 / lensPeriod * remainingDay

        val view = RemoteViews(context.packageName, R.layout.widget_progress_bar_type)
        view.apply {
            setTextViewText(R.id.tv_exchange_date, exchangeDate)
            setOnClickPendingIntent(R.id.widget_progress_bar_type, pendingIntent)
            if (isUsingContactLens) {
                if (remainingDay > 0) {
                    setTextViewText(R.id.tv_remaining_day, context.getString(R.string.text_remaining_day, remainingDay))
                    setProgressBar(R.id.progress_bar, 100, section, false)
                } else {
                    setViewVisibility(R.id.progress_bar, View.GONE)
                    setViewVisibility(R.id.progress_bar_expired, View.VISIBLE)
                }
            } else {
                setTextViewText(R.id.tv_remaining_day, context.getString(R.string.text_remaining_day, lensPeriod))
                setProgressBar(R.id.progress_bar, 100, 100, false)
            }
        }
        appWidgetManager.updateAppWidget(appWidgetId, view)
    }
}