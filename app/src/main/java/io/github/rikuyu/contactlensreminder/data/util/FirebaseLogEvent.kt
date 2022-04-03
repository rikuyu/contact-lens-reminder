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

    companion object {
        private const val UUID = "uuid"
    }
}