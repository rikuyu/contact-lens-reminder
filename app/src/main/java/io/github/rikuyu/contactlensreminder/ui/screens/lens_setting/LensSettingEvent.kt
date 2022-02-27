package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

sealed class LensSettingEvent {

    data class LensType(val lensType: Int) : LensSettingEvent()

    data class LensPeriod(val lensPeriod: Int) : LensSettingEvent()

    data class IsUseNotification(val isUseNotification: Boolean) : LensSettingEvent()

    data class NotificationDay(val notificationType: Int) : LensSettingEvent()

    data class NotificationTimeHour(val notificationTimeHour: Int) : LensSettingEvent()

    data class NotificationTimeMinute(val notificationTimeMinute: Int) : LensSettingEvent()

    data class IsShowLensPowerSection(val isShowLensPowerSection: Boolean) : LensSettingEvent()

    data class LeftPower(val leftLensPower: String) : LensSettingEvent()

    data class RightPower(val rightLensPower: String) : LensSettingEvent()

    object SaveLensSetting : LensSettingEvent()
}