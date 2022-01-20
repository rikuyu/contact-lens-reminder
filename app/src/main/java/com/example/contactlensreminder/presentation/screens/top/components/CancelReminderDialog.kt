package com.example.contactlensreminder.presentation.screens.top.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.contactlensreminder.R

@Composable
fun CancelReminderDialog(
    dialogState: Boolean,
    changeDialogState: (Boolean) -> Unit,
    cancelReminder: (Boolean) -> Unit
) {
    if (dialogState) {
        AlertDialog(
            onDismissRequest = { },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_content)
                )
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
                    onClick = {
                        changeDialogState(false)
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_cancel))
                }
            },
        )
    }
}