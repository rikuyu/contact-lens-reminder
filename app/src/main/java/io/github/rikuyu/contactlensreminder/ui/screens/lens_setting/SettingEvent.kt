package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

sealed class SettingEvent {

    data class LensType(val lensType: Int) : SettingEvent()

    data class LensPeriod(val lensPeriod: Int) : SettingEvent()

    data class IsUseNotification(val isUseNotification: Boolean) : SettingEvent()

    data class NotificationDay(val notificationType: Int) : SettingEvent()

    data class NotificationTimeHour(val notificationTimeHour: Int) : SettingEvent()

    data class NotificationTimeMinute(val notificationTimeMinute: Int) : SettingEvent()

    data class IsShowLensPowerSection(val isShowLensPowerSection: Boolean) : SettingEvent()

    data class LeftPower(val leftLensPower: String) : SettingEvent()

    data class RightPower(val rightLensPower: String) : SettingEvent()

    object SaveSetting : SettingEvent()
}