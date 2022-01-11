package com.example.contactlensreminder.presentation.ui.screens.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactlensreminder.presentation.ui.theme.Gray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun TopScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SimpleSpacer(height = 8.dp)
        Row(
            modifier = Modifier.background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(7f))
            IconButton(
                onClick = { navController.navigate(Routes.APP_SETTING) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Gray,
                    modifier = Modifier.size(36.dp, 36.dp)
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
        }
        SimpleSpacer(height = 16.dp)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .background(Color.White)
        ) {
            RemainingDaysBar(days = 5f)
        }
        LensChanageButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        )
        LensSettingButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        ) {
            navController.navigate(Routes.LENS_SETTING)
        }
        SimpleSpacer(height = 60.dp)
    }
}