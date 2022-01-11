package com.example.contactlensreminder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactlensreminder.presentation.ui.screens.app_setting.AppSettingScreen
import com.example.contactlensreminder.presentation.ui.screens.lens_setting.LensSettingScreen
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
                NavHost(
                    navController = navController,
                    startDestination = "${Routes.TOP}/{period}/{day}"
                ) {
                    composable(
                        route = "${Routes.TOP}/{period}/{day}",
                        arguments = listOf(
                            navArgument("period") { type = NavType.IntType },
                            navArgument("day") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        var period = backStackEntry.arguments?.getInt("period")
                        var remainingDays = backStackEntry.arguments?.getInt("day")
                        if (period == 0 || period == null) period = 14
                        if (remainingDays == 0 || remainingDays == null) remainingDays = 7
                        TopScreen(navController, period, remainingDays)
                    }
                    composable(route = Routes.LENS_SETTING) {
                        LensSettingScreen(navController)
                    }
                    composable(route = Routes.APP_SETTING) {
                        AppSettingScreen(navController)
                    }
                }
            }
        }
    }
}