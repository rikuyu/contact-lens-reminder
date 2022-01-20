package com.example.contactlensreminder.data.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TickDownWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val sharedPreferencesManager: SharedPreferencesManager =
        SharedPreferencesManager(context)

    private val changeAppIcon: ChangeAppIcon = ChangeAppIcon(context)

    override fun doWork(): Result {

        val before = sharedPreferencesManager.getContactLensElapsedDays()
        val after = before - 1
        sharedPreferencesManager.saveContactLensElapsedDays(after)

        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()

        changeAppIcon.changeAppIcon(context, isUsingContactLens, after)

        return Result.success()
    }
}