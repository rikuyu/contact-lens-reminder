package io.github.rikuyu.contactlensreminder.ui

import android.app.AlarmManager
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingViewModel
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.inquiry.component.ContactUsScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.instruction_screen.component.InstructionScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.main_screen.components.AppSettingScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.terms_of_service_screen.component.TermsOfServiceScreen
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.LensSettingScreen
import io.github.rikuyu.contactlensreminder.ui.screens.top.ReminderViewModel
import io.github.rikuyu.contactlensreminder.ui.screens.top.components.TopScreen
import io.github.rikuyu.contactlensreminder.ui.util.AppReviewService
import io.github.rikuyu.contactlensreminder.ui.util.AppUpdateService
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.theme.ContactLensReminderTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appUpdateService: AppUpdateService

    @Inject
    lateinit var appReviewService: AppReviewService

    @Inject
    lateinit var firebaseLogEventService: FirebaseLogEventService

    private val reminderViewModel: ReminderViewModel by viewModels()
    private val appSettingViewModel: AppSettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appUpdateService.executeAppUpdate(this)

        setContent {
            var isDarkTheme by remember { reminderViewModel.isDarkTheme }
            var themeColor by remember { appSettingViewModel.themeColor }
            val systemUiController = rememberSystemUiController()

            systemUiController.setStatusBarColor(themeColor.color)

            ContactLensReminderTheme(isDarkTheme, themeColor) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TOP
                ) {
                    composable(route = Routes.TOP) {
                        TopScreen(
                            isDarkTheme = isDarkTheme,
                            switchDarkTheme = { isDarkTheme = it },
                            requestExactAlarmPermission = { requestExactAlarmPermission(this@MainActivity) },
                            navController = navController
                        )
                    }
                    composable(route = Routes.LENS_SETTING) {
                        LensSettingScreen(
                            isDarkTheme,
                            themeColor,
                            navController
                        )
                    }
                    composable(route = Routes.APP_SETTING) {
                        AppSettingScreen(
                            themeColor = themeColor,
                            changeThemeColor = { themeColor = it },
                            executeAppReview = { appReviewService.showAppReviewBottomSheet(this@MainActivity) },
                            navController = navController
                        )
                    }
                    composable(route = Routes.TERMS_OF_SERVICE) { TermsOfServiceScreen(navController) }
                    composable(route = Routes.HELP) { InstructionScreen(navController) }
                    composable(route = Routes.INQUIRY) { ContactUsScreen(navController) }
                }
            }
        }
    }

    private fun requestExactAlarmPermission(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()
            firebaseLogEventService.logEvent(
                context.getString(R.string.exact_alarm_dialog_event_label, canScheduleExactAlarms.toString())
            )
            if (!canScheduleExactAlarms) {
                showAlertDialog(
                    context = context,
                    title = R.string.exact_alarm_dialog_title,
                    message = R.string.exact_alarm_dialog_message,
                    positiveButtonLabel = R.string.exact_alarm_dialog_positive_button_label
                ) {
                    Intent().apply {
                        action = ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                    }.also {
                        startActivity(it)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_UPDATE_CODE) {
            showAlertDialog(
                context = this,
                title = R.string.update_dialog_title,
                message = R.string.update_dialog_message,
                positiveButtonLabel = R.string.update_dialog_positive_button_label
            ) {
                appUpdateService.executeAppUpdate(this)
            }
        }
    }

    private fun showAlertDialog(
        context: Context,
        @DrawableRes icon: Int = R.drawable.icon_default,
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes positiveButtonLabel: Int,
        event: () -> Unit,
    ) {
        val dialog = AlertDialog.Builder(context)
            .setIcon(icon)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonLabel) { _, _ ->
                event.invoke()
            }
            .setCancelable(false)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    companion object {
        const val REQUEST_UPDATE_CODE = 11111111
    }
}