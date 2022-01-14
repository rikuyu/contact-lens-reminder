package com.example.contactlensreminder.presentation.screens.top.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactlensreminder.presentation.screens.top.ReminderEvent
import com.example.contactlensreminder.presentation.screens.top.ReminderViewModel
import com.example.contactlensreminder.presentation.theme.Gray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun TopScreen(
    navController: NavController,
    viewModel: ReminderViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val reminderValue = viewModel.reminder.value

    var isUsingContactLens by remember { mutableStateOf(reminderValue.isUsingContactLens) }

    val lensPeriod by remember { mutableStateOf(reminderValue.lensPeriod) }

    val lensElapsedDays by remember { mutableStateOf(reminderValue.elapsedDays) }

    val notificationTimeHour by remember { mutableStateOf(reminderValue.notificationTimeHour) }

    val notificationTimeMinute by remember { mutableStateOf(reminderValue.notificationTimeMinute) }

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
        SimpleSpacer(height = 8.dp)
        LensPeriodTextSection(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            period = lensPeriod
        )
        SimpleSpacer(height = 4.dp)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .background(Color.White)
        ) {
            RemainingDaysBar(
                lensPeriod = lensPeriod,
                notificationTimeHour = notificationTimeHour,
                notificationTimeMinute = notificationTimeMinute,
                lensElapsedDays = lensElapsedDays
            )
        }
        HandleReminderButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            isUsingContactLens = isUsingContactLens,
            changeIsUsingContactLens = { isUsingContactLens = !isUsingContactLens },
            startReminderEvent = {
                viewModel.onEvent(
                    ReminderEvent.StartReminder(
                        lensPeriod = lensPeriod,
                        notificationTimeHour = notificationTimeHour,
                        notificationTimeMinute = notificationTimeMinute,
                        elapsedDays = lensElapsedDays,
                        isUsingContactLens = isUsingContactLens
                    )
                )
            },
            stopReminderEvent = {
                Toast.makeText(
                    context,
                    "Stop",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEvent(ReminderEvent.CancelReminder)
            }
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