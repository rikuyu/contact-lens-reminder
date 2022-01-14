package com.example.contactlensreminder.presentation.screens.top

sealed class ReminderEvent {

    data class StartReminder(
        val lensPeriod: Int,
        val elapsedDays: Int,
        val notificationTimeHour: Int,
        val notificationTimeMinute: Int,
        val isUsingContactLens: Boolean
    ) : ReminderEvent()

    object CancelReminder : ReminderEvent()
}