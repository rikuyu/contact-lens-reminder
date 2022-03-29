package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
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
import io.github.rikuyu.contactlensreminder.ui.screens.top.ReminderEvent
import io.github.rikuyu.contactlensreminder.ui.screens.top.ReminderViewModel
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun TopScreen(
    isDarkTheme: Boolean,
    switchDarkTheme: (Boolean) -> Unit,
    executeAppReview: () -> Unit,
    navController: NavController,
    viewModel: ReminderViewModel = hiltViewModel(),
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
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = if (isDarkTheme) R.drawable.ic_light_mode else R.drawable.ic_dark_mode),
                contentDescription = null,
                tint = if (isDarkTheme) Color.White else Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .size(32.dp, 32.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            color = MaterialTheme.colors.primary,
                            bounded = false,
                            radius = 28.dp
                        )
                    ) {
                        switchDarkTheme(!isDarkTheme)
                        viewModel.onEvent(ReminderEvent.SwitchIsDarkTheme)
                    }
            )
            Spacer(modifier = Modifier.weight(7f))
            Icon(
                painter = painterResource(id = R.drawable.ic_help),
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .weight(1f)
                    .size(36.dp, 36.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            color = MaterialTheme.colors.primary,
                            bounded = false,
                            radius = 28.dp
                        )
                    ) { navController.navigate(Routes.APP_SETTING) }
            )
        }
        LensPeriodTextSection(
            modifier = Modifier.fillMaxWidth(),
            lensRemainingDays = lensRemainingDays,
            period = lensPeriod
        )
        SimpleSpacer(height = 4.dp, color = MaterialTheme.colors.background)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
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
        HandleReminderButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
            changeDialogState = { dialogState = it },
            executeAppReview = { executeAppReview() }
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
                .weight(1f),
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