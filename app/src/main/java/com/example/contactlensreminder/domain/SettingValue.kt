package com.example.contactlensreminder.domain

data class SettingValue(
    val lensType: Int = 0,
    val lensPeriod: Int = 14,
    val isUseNotification: Boolean = true,
    val notificationDay: Int = 1,
    val notificationTimeHour: Int = 9,
    val notificationTimeMinute: Int = 15,
    val isShowLensPowerSection: Boolean = false,
    val leftLensPower: Double = -4.00,
    val rightLensPower: Double = -4.00
)