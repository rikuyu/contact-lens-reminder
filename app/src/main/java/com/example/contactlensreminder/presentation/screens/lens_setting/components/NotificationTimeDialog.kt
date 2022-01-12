package com.example.contactlensreminder.presentation.screens.lens_setting.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.contactlensreminder.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.timepicker

@Composable
fun NotificationTimeDialog(
    dialogState: MaterialDialogState,
    setNotificationTime: (String) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.ok))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        timepicker(title = stringResource(id = R.string.notification_time)) { date ->
            setNotificationTime(date.toString())
        }
    }
}