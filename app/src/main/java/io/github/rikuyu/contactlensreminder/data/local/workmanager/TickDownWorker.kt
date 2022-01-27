package io.github.rikuyu.contactlensreminder.data.local.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService

class TickDownWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val sharedPreferencesManager: SharedPreferencesManager =
        SharedPreferencesManager(context)

    private val changeAppIconService: ChangeAppIconService = ChangeAppIconService(context)

    override fun doWork(): Result {
        val before = sharedPreferencesManager.getContactLensElapsedDays()
        val after = before - 1
        sharedPreferencesManager.saveContactLensElapsedDays(after)

        val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
        changeAppIconService.changeAppIcon(context, isUsingContactLens, after)

        return Result.success()
    }
}