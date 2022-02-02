package io.github.rikuyu.contactlensreminder.domain.model

data class ReminderValue(
    val lensPeriod: Int = 14,
    val lensRemainingDays: Int = 14,
    val exchangeDay: String = "0000/00/00",
    val notificationDay: Int = 0,
    val notificationTimeHour: Int = 8,
    val notificationTimeMinute: Int = 0,
    val isUsingContactLens: Boolean = false,
    val isUseNotification: Boolean = true
)