package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.ui.screens.top.ReminderEvent
import io.github.rikuyu.contactlensreminder.ui.screens.top.ReminderViewModel
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.showToast

@Composable
fun TopScreen(
    isDarkTheme: Boolean,
    switchDarkTheme: (Boolean) -> Unit,
    requestExactAlarmPermission: () -> Unit,
    navController: NavController,
    viewModel: ReminderViewModel = hiltViewModel(),
) {
    requestExactAlarmPermission.invoke()

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
        TopButtonSection(
            isDarkTheme = isDarkTheme,
            themeButtonEvent = {
                switchDarkTheme(it)
                viewModel.onEvent(ReminderEvent.SwitchIsDarkTheme)
            },
            helpButtonEvent = {
                navController.navigate(Routes.APP_SETTING)
            }
        )
        LensPeriodTextSection(
            modifier = Modifier.fillMaxWidth(),
            lensRemainingDays = lensRemainingDays,
            period = lensPeriod
        )
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
            showDialog = { dialogState = true }
        )
        ResetReminderDialog(
            dialogState = dialogState,
            changeDialogState = { dialogState = it }
        ) {
            isUsingContactLens = it
            viewModel.onEvent(
                ReminderEvent.ResetReminder(
                    ReminderValue(
                        lensPeriod = lensPeriod,
                        notificationTimeHour = notificationTimeHour,
                        notificationTimeMinute = notificationTimeMinute,
                        lensRemainingDays = lensRemainingDays,
                        isUsingContactLens = it
                    )
                )
            )
            viewModel.onEvent(ReminderEvent.GetReminderSetting)
        }
        LensSettingButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            showToast = { showToast(context, R.string.alert_toast_message) },
            isUsingContactLens = isUsingContactLens,
            navigate = { navController.navigate(Routes.LENS_SETTING) }
        )
    }
}