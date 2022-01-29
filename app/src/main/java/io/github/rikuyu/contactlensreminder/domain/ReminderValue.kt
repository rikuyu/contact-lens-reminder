package io.github.rikuyu.contactlensreminder.domain

data class ReminderValue(
    val lensPeriod: Int = 14,
    val lensRemainingDays: Int = 14,
    val exchangeDay: String = "0000/00/00",
    val notificationTimeHour: Int = 9,
    val notificationTimeMinute: Int = 15,
    val isUsingContactLens: Boolean = false,
    val isUseNotification: Boolean = true
)