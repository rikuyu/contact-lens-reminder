package com.example.contactlensreminder.presentation.screens.top

sealed class ReminderEvent {

    data class StartReminder(
        val lensPeriod: Int,
        val elapsedDays: Int,
        val notificationTime: String,
        val isUsingContactLens: Boolean
    ) : ReminderEvent()

    object CancelReminder : ReminderEvent()
}