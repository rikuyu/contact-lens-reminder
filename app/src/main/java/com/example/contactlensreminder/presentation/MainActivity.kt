package com.example.contactlensreminder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactlensreminder.presentation.Routes
import com.example.contactlensreminder.presentation.ui.setting.TopScreen
import com.example.contactlensreminder.presentation.ui.theme.ContactLensReminderTheme
import com.example.contactlensreminder.presentation.ui.top.SettingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactLensReminderTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.TOP) {
                    composable(Routes.TOP) {
                        TopScreen()
                    }
                    composable(Routes.SETTING) {
                        SettingScreen()
                    }
                }
            }
        }
    }
}
