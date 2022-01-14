package com.example.contactlensreminder.presentation.screens.lens_setting

data class SettingValue(
    val lensType: Int = 0,
    val lensPeriod: Int = 14,
    val isUseNotification: Boolean = true,
    val notificationDay: Int = 1,
    val notificationTimeHour: Int = 9,
    val notificationTimeMinute: Int = 15,
    val leftLensPower: Double = -4.00,
    val rightLensPower: Double = -4.00
)