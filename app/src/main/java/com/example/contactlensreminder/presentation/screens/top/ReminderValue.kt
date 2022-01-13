package com.example.contactlensreminder.presentation.screens.top

data class ReminderValue(
    val lensPeriod: Int = 14,
    val elapsedDays: Int = 14,
    val notificationTime: String = "",
    val isUsingContactLens: Boolean = false
)