package io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val lensSettingUseCase: LensSettingUseCase
) : ViewModel() {

    private val _setting: MutableState<SettingValue> = mutableStateOf(SettingValue())
    val setting: State<SettingValue> = _setting

    init {
        _setting.value = lensSettingUseCase.getAllSetting.invoke()
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.LensType -> {
                _setting.value = setting.value.copy(
                    lensType = event.lensType
                )
            }
            is SettingEvent.LensPeriod -> {
                _setting.value = setting.value.copy(
                    lensPeriod = event.lensPeriod
                )
            }
            is SettingEvent.IsUseNotification -> {
                _setting.value = setting.value.copy(
                    isUseNotification = event.isUseNotification
                )
            }
            is SettingEvent.NotificationDay -> {
                _setting.value = setting.value.copy(
                    notificationDay = event.notificationType
                )
            }
            is SettingEvent.NotificationTimeHour -> {
                _setting.value = setting.value.copy(
                    notificationTimeHour = event.notificationTimeHour
                )
            }
            is SettingEvent.NotificationTimeMinute -> {
                _setting.value = setting.value.copy(
                    notificationTimeMinute = event.notificationTimeMinute
                )
            }
            is SettingEvent.IsShowLensPowerSection -> {
                _setting.value = setting.value.copy(
                    isShowLensPowerSection = event.isShowLensPowerSection
                )
            }
            is SettingEvent.LeftPower -> {
                _setting.value = setting.value.copy(
                    leftLensPower = event.leftLensPower
                )
            }
            is SettingEvent.RightPower -> {
                _setting.value = setting.value.copy(
                    rightLensPower = event.rightLensPower
                )
            }
            is SettingEvent.SaveSetting -> lensSettingUseCase.saveAllSetting(setting.value)
        }
    }
}