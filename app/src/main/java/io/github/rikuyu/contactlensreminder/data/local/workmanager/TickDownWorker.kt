package io.github.rikuyu.contactlensreminder.data.local.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import javax.inject.Inject

class TickDownWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var changeAppIconService: ChangeAppIconService

    override fun doWork(): Result {
        val before = sharedPreferencesManager.getContactLensRemainingDays()
        val after = before - 1
        sharedPreferencesManager.saveContactLensRemainingDays(after)

        return when (before) {
            sharedPreferencesManager.getContactLensRemainingDays() + 1 -> {
                val isUsingContactLens = sharedPreferencesManager.getIsUsingContactLens()
                changeAppIconService.changeAppIcon(context, isUsingContactLens, after)
                Result.success()
            }
            sharedPreferencesManager.getContactLensRemainingDays() -> Result.retry()
            else -> Result.failure()
        }
    }
}