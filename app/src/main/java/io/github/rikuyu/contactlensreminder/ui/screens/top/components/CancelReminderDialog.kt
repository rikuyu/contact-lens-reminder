package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.rikuyu.contactlensreminder.R

@Composable
fun CancelReminderDialog(
    dialogState: Boolean,
    changeDialogState: (Boolean) -> Unit,
    cancelReminder: (Boolean) -> Unit
) {
    if (dialogState) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = stringResource(id = R.string.dialog_title))
            },
            text = {
                Text(text = stringResource(id = R.string.dialog_content))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        cancelReminder(false)
                        changeDialogState(false)
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { changeDialogState(false) }
                ) {
                    Text(text = stringResource(id = R.string.btn_cancel))
                }
            }
        )
    }
}