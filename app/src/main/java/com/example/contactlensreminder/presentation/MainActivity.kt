package com.example.contactlensreminder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactlensreminder.presentation.ui.screens.appsetting.AppSettingScreen
import com.example.contactlensreminder.presentation.ui.screens.lenssetting.LensSettingScreen
import com.example.contactlensreminder.presentation.ui.screens.top.TopScreen
import com.example.contactlensreminder.presentation.ui.theme.ContactLensReminderTheme
import com.example.contactlensreminder.presentation.util.Routes

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactLensReminderTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.TOP) {
                    composable(Routes.TOP) {
                        TopScreen(navController)
                    }
                    composable(Routes.LENS_SETTING) {
                        LensSettingScreen(navController)
                    }
                    composable(Routes.APP_SETTING) {
                        AppSettingScreen(navController)
                    }
                }
            }
        }
    }
}