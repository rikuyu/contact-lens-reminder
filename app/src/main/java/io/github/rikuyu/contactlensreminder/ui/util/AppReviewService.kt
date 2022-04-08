package io.github.rikuyu.contactlensreminder.ui.util

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import javax.inject.Inject

class AppReviewService @Inject constructor(
    private val firebaseLogEventService: FirebaseLogEventService,
) {
    fun showAppReviewBottomSheet(activity: Activity) {
        val manager = ReviewManagerFactory.create(activity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                manager.launchReviewFlow(activity, task.result)
                    .addOnCompleteListener {
                        firebaseLogEventService.logEvent(activity.getString(R.string.review_launch_complete))
                    }
                    .addOnFailureListener {
                        firebaseLogEventService.logEvent(activity.getString(R.string.review_launch_failure))
                    }
            } else {
                firebaseLogEventService.logEvent(activity.getString(R.string.review_request_not_complete))
            }
        }
    }
}