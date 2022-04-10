package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.View
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.getExpirationDate
import io.github.rikuyu.contactlensreminder.ui.MainActivity
import io.github.rikuyu.contactlensreminder.ui.util.getEnglishDay
import java.util.*

class ProgressBarTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
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
            setOnClickPendingIntent(
                R.id.widget_progress_bar_type,
                pendingIntent
            )

            when (Locale.getDefault().language) {
                Locale.JAPANESE.language -> {
                    setTextViewText(R.id.tv_exchange_date, exchangeDate)
                    setViewVisibility(R.id.tv_remaining_day_before, View.VISIBLE)
                    setViewVisibility(R.id.tv_remaining_day, View.VISIBLE)
                    setTextViewText(R.id.tv_remaining_day, remainingDay.toString())
                    setViewVisibility(R.id.tv_remaining_day_after, View.VISIBLE)
                }
                Locale.ENGLISH.language -> {
                    val (date, d) = exchangeDate.split(" ")
                    val day = getEnglishDay(d.replace("(", "").replace(")", ""))
                    setTextViewText(
                        R.id.tv_exchange_date,
                        "$day $date"
                    )

                    setViewVisibility(R.id.tv_remaining_day_before, View.VISIBLE)
                    setTextViewText(R.id.tv_remaining_day_before, remainingDay.toString())
                    setTextViewTextSize(R.id.tv_remaining_day_before, TypedValue.COMPLEX_UNIT_SP, 16F)

                    setTextViewText(
                        R.id.tv_remaining_day,
                        if (remainingDay == 1)
                            " ${context.getString(R.string.text_remaining_day_after)}"
                        else
                            " ${context.getString(R.string.text_remaining_day_after)}s"
                    )
                    setTextViewTextSize(R.id.tv_remaining_day, TypedValue.COMPLEX_UNIT_SP, 14F)

                    setViewVisibility(R.id.tv_remaining_day_after, View.GONE)
                }
                else -> {
                    setTextViewText(R.id.tv_exchange_date, exchangeDate)
                    setViewVisibility(R.id.tv_remaining_day_before, View.VISIBLE)
                    setViewVisibility(R.id.tv_remaining_day, View.VISIBLE)
                    setTextViewText(R.id.tv_remaining_day, remainingDay.toString())
                    setViewVisibility(R.id.tv_remaining_day_after, View.VISIBLE)
                }
            }

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
        private const val REQUEST_CODE_ACTIVITY = 666666
    }
}