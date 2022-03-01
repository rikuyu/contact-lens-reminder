package io.github.rikuyu.contactlensreminder.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.inquiry.component.ContactUsScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.instruction_screen.component.InstructionScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.main_screen.components.AppSettingScreen
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.terms_of_service_screen.component.TermsOfServiceScreen
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.LensSettingScreen
import io.github.rikuyu.contactlensreminder.ui.screens.top.components.TopScreen
import io.github.rikuyu.contactlensreminder.ui.theme.ContactLensReminderTheme
import io.github.rikuyu.contactlensreminder.ui.util.AppUpdateService
import io.github.rikuyu.contactlensreminder.ui.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppUpdateService(this).executeAppUpdate(this)

        setContent {
            ContactLensReminderTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TOP
                ) {
                    composable(route = Routes.TOP) {
                        TopScreen(navController)
                    }
                    composable(route = Routes.LENS_SETTING) {
                        LensSettingScreen(navController)
                    }
                    composable(route = Routes.APP_SETTING) {
                        AppSettingScreen(navController)
                    }
                    composable(route = Routes.TERMS_OF_SERVICE) {
                        TermsOfServiceScreen(navController)
                    }
                    composable(route = Routes.HELP) {
                        InstructionScreen(navController)
                    }
                    composable(route = Routes.INQUIRY) {
                        ContactUsScreen(navController)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_UPDATE_CODE) {
            val dialog = AlertDialog.Builder(this)
                .setIcon(R.drawable.icon_default)
                .setTitle(R.string.update_dialog_title)
                .setMessage(R.string.update_dialog_message)
                .setPositiveButton(R.string.update_dialog_positive_button_label) { _, _ ->
                    AppUpdateService(this).executeAppUpdate(this)
                }
                .setCancelable(false)
                .create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

    companion object {
        const val REQUEST_UPDATE_CODE = 11111111
    }
}