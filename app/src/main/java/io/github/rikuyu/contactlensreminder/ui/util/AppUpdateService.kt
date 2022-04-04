package io.github.rikuyu.contactlensreminder.ui.util

import android.app.Activity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import javax.inject.Inject

class AppUpdateService @Inject constructor(
    private val firebaseLogEventService: FirebaseLogEventService,
) {
    fun executeAppUpdate(activity: Activity) {
        val appUpdateManager = AppUpdateManagerFactory.create(activity)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isImmediateUpdateAllowed
            ) {
                firebaseLogEventService.logEvent(
                    activity.getString(R.string.update_available)
                )
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        info,
                        AppUpdateType.IMMEDIATE,
                        activity,
                        REQUEST_UPDATE_CODE
                    )
                    firebaseLogEventService.logEvent(
                        activity.getString(R.string.update_immediate_success)
                    )
                } catch (e: Exception) {
                    firebaseLogEventService.logEvent(e.toString())
                }
            } else {
                when (info.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {
                        firebaseLogEventService.logEvent(
                            activity.getString(R.string.update_unknown)
                        )
                    }
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        firebaseLogEventService.logEvent(
                            activity.getString(R.string.update_not_available)
                        )
                    }
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        firebaseLogEventService.logEvent(
                            activity.getString(R.string.update_triggered_in_progress)
                        )
                    }
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        // NOP
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_UPDATE_CODE = 11111111
    }
}