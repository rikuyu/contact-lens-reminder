package com.example.contactlensreminder.presentation

sealed class LensEvent {

    data class LensType(val lensType: Int) : LensEvent()

    data class LensPeriod(val lensPeriod: Int) : LensEvent()

    data class IsUseNotification(val isUseNotification: Boolean) : LensEvent()

    data class NotificationDay(val notificationType: Int) : LensEvent()

    data class LeftLensPower(val leftLensPower: Double) : LensEvent()

    data class RightLensPower(val rightLensPower: Double) : LensEvent()

    object SaveLensPower : LensEvent()

    object SaveSetting : LensEvent()
}