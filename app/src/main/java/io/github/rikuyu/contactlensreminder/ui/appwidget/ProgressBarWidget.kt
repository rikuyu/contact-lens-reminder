package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.view.View
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager

class ProgressBarWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateProgressBarWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateProgressBarWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays()
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod()
        val exchangeDate = sharedPreferencesManager.getLensExchangeDay() ?: ""

        val section = 100 / lensPeriod * remainingDay

        val view = RemoteViews(context.packageName, R.layout.widget_progress_bar)
        view.apply {
            setTextViewText(R.id.tv_exchange_date, exchangeDate)
            if (remainingDay > 0) {
                setTextViewText(R.id.tv_remaining_day, context.getString(R.string.text_remaining_day, remainingDay))
                setProgressBar(R.id.progress_bar, 100, section, false)
            } else {
                setViewVisibility(R.id.progress_bar, View.GONE)
                setViewVisibility(R.id.progress_bar_expired, View.VISIBLE)
            }
        }
        appWidgetManager.updateAppWidget(appWidgetId, view)
    }
}