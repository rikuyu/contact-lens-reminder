package io.github.rikuyu.contactlensreminder.ui.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager

class ImageTypeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateImageTypeWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateImageTypeWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val view = RemoteViews(context.packageName, R.layout.widget_image_type)
        appWidgetManager.updateAppWidget(appWidgetId, view)
    }
}