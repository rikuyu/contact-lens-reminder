package com.example.contactlensreminder.presentation.screens.top

sealed class ReminderEvent(val reminderValue: ReminderValue) {

    data class StartReminder(
        val data: ReminderValue
    ) : ReminderEvent(data)

    data class CancelReminder(
        val data: ReminderValue
    ) : ReminderEvent(data)
}