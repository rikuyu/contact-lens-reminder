package com.example.contactlensreminder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactlensreminder.presentation.screens.app_setting.inquiry.component.ContactUsScreen
import com.example.contactlensreminder.presentation.screens.app_setting.main_screen.components.AppSettingScreen
import com.example.contactlensreminder.presentation.screens.app_setting.terms_of_service.component.TermsOfService
import com.example.contactlensreminder.presentation.screens.lens_setting.components.LensSettingScreen
import com.example.contactlensreminder.presentation.screens.top.components.TopScreen
import com.example.contactlensreminder.presentation.theme.ContactLensReminderTheme
import com.example.contactlensreminder.presentation.util.Routes

import dagger.hilt.android.AndroidEntryPoint

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
                        TermsOfService(navController)
                    }
                    composable(route = Routes.HELP) {
                    }
                    composable(route = Routes.INQUIRY) {
                        ContactUsScreen(navController)
                    }
                }
            }
        }
    }
}