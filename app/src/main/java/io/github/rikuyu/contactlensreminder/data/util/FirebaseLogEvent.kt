package io.github.rikuyu.contactlensreminder.data.util

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import javax.inject.Inject

class FirebaseLogEvent @Inject constructor(val sharedPreferencesManager: SharedPreferencesManager) {

    fun logEvent(label: String) {
        val uuid = sharedPreferencesManager.getUuid() ?: return
        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent(label) { param(UUID, uuid) }
    }

    fun logInitTickDownEvent() {
        val uuid = sharedPreferencesManager.getUuid() ?: return
        val lensPeriod = sharedPreferencesManager.getContactLensPeriod().toString()
        val remainingDay = sharedPreferencesManager.getContactLensRemainingDays().toString()

        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("init_tick_down_alarm_event") {
            param(UUID, uuid)
            param(LENS_PERIOD, lensPeriod)
            param(REMAINING_DAY, remainingDay)
        }
    }

    companion object {
        private const val LENS_PERIOD = "lens_period"
        private const val REMAINING_DAY = "remaining_day"
        private const val UUID = "uuid"
    }
}