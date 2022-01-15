package com.example.contactlensreminder.presentation.screens.top

sealed class ReminderEvent {

    data class StartReminder(
        val reminderValue: ReminderValue
    ) : ReminderEvent()

    data class CancelReminder(
        val reminderValue: ReminderValue
    ) : ReminderEvent()
}