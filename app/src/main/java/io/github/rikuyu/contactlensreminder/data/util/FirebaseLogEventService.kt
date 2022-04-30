package io.github.rikuyu.contactlensreminder.data.util

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import java.util.*
import javax.inject.Inject

class FirebaseLogEventService @Inject constructor(val sharedPreferencesManager: SharedPreferencesManager) {

    fun logEvent(label: String) {
        sharedPreferencesManager.apply {
            if (getIsFirstUse()) {
                saveIsFirstUse()
                saveUuid(UUID.randomUUID().toString())
            }
        }
        val uuid = sharedPreferencesManager.getUuid() ?: return
        Firebase.analytics.logEvent(label) { param("uuid", uuid) }
    }
}