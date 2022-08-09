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

    val reminderValue by viewModel.reminder

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
            lensRemainingDays = reminderValue.lensRemainingDays,
            period = reminderValue.lensPeriod
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        ) {
            RemainingDaysBar(
                lensPeriod = reminderValue.lensPeriod,
                exchangeDay = reminderValue.exchangeDay,
                isUsingContactLens = reminderValue.isUsingContactLens,
                notificationDay = reminderValue.notificationDay,
                notificationTimeHour = reminderValue.notificationTimeHour,
                notificationTimeMinute = reminderValue.notificationTimeMinute,
                lensRemainingDays = reminderValue.lensRemainingDays,
                isUseNotification = reminderValue.isUseNotification
            )
        }
        HandleReminderButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            lensRemainingDays = reminderValue.lensRemainingDays,
            isUsingContactLens = reminderValue.isUsingContactLens,
            startReminder = {
                viewModel.onEvent(
                    ReminderEvent.StartReminder(
                        ReminderValue(
                            lensPeriod = reminderValue.lensPeriod,
                            notificationTimeHour = reminderValue.notificationTimeHour,
                            notificationTimeMinute = reminderValue.notificationTimeMinute,
                            lensRemainingDays = reminderValue.lensRemainingDays,
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
            viewModel.onEvent(
                ReminderEvent.ResetReminder(
                    ReminderValue(
                        lensPeriod = reminderValue.lensPeriod,
                        notificationTimeHour = reminderValue.notificationTimeHour,
                        notificationTimeMinute = reminderValue.notificationTimeMinute,
                        lensRemainingDays = reminderValue.lensRemainingDays,
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
            isUsingContactLens = reminderValue.isUsingContactLens,
            navigate = { navController.navigate(Routes.LENS_SETTING) }
        )
    }
}