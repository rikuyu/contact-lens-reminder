package io.github.rikuyu.contactlensreminder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.inquiry.component.ContactUsScreen
import io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.instruction_screen.component.InstructionScreen
import io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.main_screen.components.AppSettingScreen
import io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.terms_of_service_screen.component.TermsOfServiceScreen
import io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.components.LensSettingScreen
import io.github.rikuyu.contactlensreminder.presentation.screens.top.components.TopScreen
import io.github.rikuyu.contactlensreminder.presentation.theme.ContactLensReminderTheme
import io.github.rikuyu.contactlensreminder.presentation.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}