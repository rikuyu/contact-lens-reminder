package io.github.rikuyu.contactlensreminder.ui.util

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEvent
import javax.inject.Inject

class AppReviewService @Inject constructor(
    private val firebaseLogEvent: FirebaseLogEvent,
) {
    fun showAppReviewView(activity: Activity) {
        val manager = ReviewManagerFactory.create(activity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                manager.launchReviewFlow(activity, reviewInfo)
                    .addOnCompleteListener { _ ->
                        firebaseLogEvent.logEvent(activity.getString(R.string.review_launch_complete))
                    }
                    .addOnFailureListener {
                        firebaseLogEvent.logEvent(activity.getString(R.string.review_launch_failure))
                    }
            } else {
                firebaseLogEvent.logEvent(activity.getString(R.string.review_request_not_complete))
            }
        }
    }
}