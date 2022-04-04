package io.github.rikuyu.contactlensreminder.ui.util

import android.app.Activity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import javax.inject.Inject

class AppUpdateService @Inject constructor(
    private val firebaseLogEvent: FirebaseLogEvent,
) {
    fun executeAppUpdate(activity: Activity) {
        val appUpdateManager = AppUpdateManagerFactory.create(activity)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isImmediateUpdateAllowed
            ) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        info,
                        AppUpdateType.IMMEDIATE,
                        activity,
                        REQUEST_UPDATE_CODE
                    )
                    firebaseLogEvent.logEvent(
                        activity.getString(R.string.update_immediate_success)
                    )
                } catch (e: Exception) {
                    firebaseLogEvent.logEvent(e.toString())
                }
            }
        }
    }

    companion object {
        const val REQUEST_UPDATE_CODE = 11111111
    }
}