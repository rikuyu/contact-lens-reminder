package io.github.rikuyu.contactlensreminder.domain.model

data class SettingValue(
    val lensType: Int = 0,
    val lensPeriod: Int = 14,
    val isUseNotification: Boolean = false,
    val notificationDay: Int = 1,
    val notificationTimeHour: Int = 8,
    val notificationTimeMinute: Int = 0,
    val isShowLensPowerSection: Boolean = false,
    val leftLensPower: String = "-4.00",
    val rightLensPower: String = "-4.00"
)