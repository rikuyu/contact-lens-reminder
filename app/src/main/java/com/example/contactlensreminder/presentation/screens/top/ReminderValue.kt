package com.example.contactlensreminder.presentation.screens.top

data class ReminderValue(
    val lensPeriod: Int = 14,
    val elapsedDays: Int = 14,
    val notificationTimeHour: Int = 9,
    val notificationTimeMinute: Int = 15,
    val isUsingContactLens: Boolean = false
)