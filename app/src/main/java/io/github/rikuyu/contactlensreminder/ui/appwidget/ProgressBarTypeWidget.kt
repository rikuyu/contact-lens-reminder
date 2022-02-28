package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.createIntent
import io.github.rikuyu.contactlensreminder.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class ProgressBarTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        context.applicationContext.registerReceiver(this, IntentFilter(Intent.ACTION_USER_PRESENT))

        for (appWidgetId in appWidgetIds) {
            updateProgressBarTypeWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        Log.d("AAAAAAAAAA", "onEnabled")
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(INTENT_CODE)
        alarmManager.cancel(createIntent(context, intent, PROGRESS_BAR_WIDGET_CODE))
        Log.d("AAAAAAAAAA", "disable")
    }

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)

        val action = intent?.action ?: return
        Log.d("AAAAAAAAAA", "action $action")
        if (action == INTENT_CODE) {
            Log.d("AAAAAAAAAA", "rece if $action")
            val thisAppWidget = ComponentName(context.packageName, javaClass.name)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val ids = appWidgetManager.getAppWidgetIds(thisAppWidget)
            for (appWidgetID in ids) {
                updateProgressBarTypeWidget(context, appWidgetManager, appWidgetID)
            }
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
        val exchangeDate = sharedPreferencesManager.getLensExchangeDay() ?: "null"
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
            setOnClickPendingIntent(
                R.id.widget_progress_bar_type,
                pendingIntent
            )
            setTextViewText(R.id.tv_remaining_day, context.getString(R.string.text_remaining_day, remainingDay))
            if (isUsingContactLens) {
                if (remainingDay > 0) {
                    setProgressBar(R.id.progress_bar, 100, section, false)
                } else {
                    setViewVisibility(R.id.progress_bar, View.GONE)
                    setViewVisibility(R.id.progress_bar_expired, View.VISIBLE)
                }
            } else {
                setProgressBar(R.id.progress_bar, 100, 100, false)
            }
        }
        Log.d("AAAAAAAAAA", "updateProgressBarTypeWidget")

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ProgressBarTypeWidget::class.java)
        intent.action = INTENT_CODE
        val pi = PendingIntent.getBroadcast(context, PROGRESS_BAR_WIDGET_CODE, intent, PendingIntent.FLAG_IMMUTABLE)
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.ENGLISH)
        val (hour, min, sec) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.HOUR, 24 - hour)
            add(Calendar.MINUTE, -min)
            add(Calendar.SECOND, -sec + 5)
        }
        alarmManager.setExact(
            AlarmManager.RTC,
            calendar.timeInMillis,
            pi
        )
        appWidgetManager.updateAppWidget(appWidgetId, view)
    }

    companion object {
        private const val INTENT_CODE = "PROGRESS_BAR_TYPE_WIDGET_TICK_DOWN"
        private const val PROGRESS_BAR_WIDGET_CODE = 777778
    }
}