package com.example.contactlensreminder.presentation.screens.lens_setting

sealed class SettingEvent {

    data class Type(val lensType: Int) : SettingEvent()

    data class Period(val lensPeriod: Int) : SettingEvent()

    data class IsUseNotification(val isUseNotification: Boolean) : SettingEvent()

    data class NotificationDay(val notificationType: Int) : SettingEvent()

    data class NotificationTimeHour(val notificationTimeHour: Int) : SettingEvent()

    data class NotificationTimeMinute(val notificationTimeMinute: Int) : SettingEvent()

    data class LeftPower(val leftLensPower: Double) : SettingEvent()

    data class RightPower(val rightLensPower: Double) : SettingEvent()

    object SaveSetting : SettingEvent()
}