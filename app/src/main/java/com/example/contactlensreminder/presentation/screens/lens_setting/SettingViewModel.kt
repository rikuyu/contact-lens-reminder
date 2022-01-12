package com.example.contactlensreminder.presentation.screens.lens_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is SettingEvent.Type -> {
                _setting.value = setting.value.copy(
                    lensType = event.lensType
                )
            }
            is SettingEvent.Period -> {
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
            is SettingEvent.NotificationTime -> {
                _setting.value = setting.value.copy(
                    notificationTime = event.notificationTime
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