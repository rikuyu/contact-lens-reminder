package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.rikuyu.contactlensreminder.R

@Composable
fun CancelReminderDialog(
    dialogState: Boolean,
    color: Color = MaterialTheme.colors.primary,
    changeDialogState: (Boolean) -> Unit,
    executeAppReview: () -> Unit,
    cancelReminder: (Boolean) -> Unit,
) {
    if (dialogState) {
        AlertDialog(
            onDismissRequest = { },
            shape = RoundedCornerShape(10.dp),
            title = {
                Text(text = stringResource(id = R.string.reminder_cancel_dialog_title))
            },
            text = {
                Text(text = stringResource(id = R.string.reminder_cancel_dialog_content))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        cancelReminder(false)
                        changeDialogState(false)
                        executeAppReview()
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_ok), color = color)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { changeDialogState(false) }
                ) {
                    Text(text = stringResource(id = R.string.btn_cancel), color = color)
                }
            }
        )
    }
}