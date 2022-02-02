package io.github.rikuyu.contactlensreminder.presentation.screens.top.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.presentation.screens.top.ReminderEvent
import io.github.rikuyu.contactlensreminder.presentation.screens.top.ReminderViewModel
import io.github.rikuyu.contactlensreminder.presentation.theme.LightBlue
import io.github.rikuyu.contactlensreminder.presentation.util.Routes
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun TopScreen(
    navController: NavController,
    viewModel: ReminderViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val reminderValue = viewModel.reminder.value

    val isUseNotification = reminderValue.isUseNotification

    val lensPeriod = reminderValue.lensPeriod

    val lensRemainingDays = reminderValue.lensRemainingDays

    val exchangeDay = reminderValue.exchangeDay

    val notificationTimeHour = reminderValue.notificationTimeHour

    val notificationTimeMinute = reminderValue.notificationTimeMinute

    val notificationDay = reminderValue.notificationDay

    var isUsingContactLens = reminderValue.isUsingContactLens

    var dialogState by remember { mutableStateOf(false) }

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
                    painter = painterResource(id = R.drawable.ic_help),
                    contentDescription = null,
                    tint = LightBlue,
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
            lensRemainingDays = lensRemainingDays,
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
                exchangeDay = exchangeDay,
                isUsingContactLens = isUsingContactLens,
                notificationDay = notificationDay,
                notificationTimeHour = notificationTimeHour,
                notificationTimeMinute = notificationTimeMinute,
                lensRemainingDays = lensRemainingDays,
                isUseNotification = isUseNotification
            )
        }
        HandleReminderButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            lensRemainingDays = lensRemainingDays,
            isUsingContactLens = isUsingContactLens,
            startReminder = {
                isUsingContactLens = it
                viewModel.onEvent(
                    ReminderEvent.StartReminder(
                        ReminderValue(
                            lensPeriod = lensPeriod,
                            notificationTimeHour = notificationTimeHour,
                            notificationTimeMinute = notificationTimeMinute,
                            lensRemainingDays = lensRemainingDays,
                            isUsingContactLens = it
                        )
                    )
                )
            },
            openDialog = { dialogState = true }
        )
        CancelReminderDialog(
            dialogState = dialogState,
            changeDialogState = { dialogState = it }
        ) {
            isUsingContactLens = it
            viewModel.onEvent(
                ReminderEvent.CancelReminder(
                    ReminderValue(
                        lensPeriod = lensPeriod,
                        notificationTimeHour = notificationTimeHour,
                        notificationTimeMinute = notificationTimeMinute,
                        lensRemainingDays = lensRemainingDays,
                        isUsingContactLens = it
                    )
                )
            )
            Toast.makeText(
                context,
                context.getString(R.string.confirm_reminder_message),
                Toast.LENGTH_SHORT
            ).show()
        }
        LensSettingButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            showAlertToast = {
                Toast.makeText(
                    context,
                    context.getString(R.string.alert_toast_message),
                    Toast.LENGTH_LONG
                ).show()
            },
            isUsingContactLens = isUsingContactLens,
            navigate = { navController.navigate(Routes.LENS_SETTING) }
        )
    }
}