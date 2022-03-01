package io.github.rikuyu.contactlensreminder.ui.util

import android.app.Activity
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import javax.inject.Inject

class AppUpdateService @Inject constructor(
    context: Context,
    private val firebaseLogEvent: FirebaseLogEvent
) {
    private val appUpdateManager = AppUpdateManagerFactory.create(context)

    fun executeAppUpdate(activity: Activity) {
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