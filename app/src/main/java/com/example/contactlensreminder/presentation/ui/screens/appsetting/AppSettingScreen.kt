package com.example.contactlensreminder.presentation.ui.screens.appsetting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue

@Composable
fun AppSettingScreen(
    navController: NavController
) {
    Column {
        Button(onClick = { navController.navigate(Routes.TOP) }) {
        }

        Image(
            painter = painterResource(id = R.drawable.water_drop),
            contentDescription = null,
            colorFilter = ColorFilter.tint(CleanBlue)
        )
    }
}