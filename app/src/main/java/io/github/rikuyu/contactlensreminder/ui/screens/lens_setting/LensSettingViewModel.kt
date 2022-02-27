package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting.LensSettingUseCase
import javax.inject.Inject

@HiltViewModel
class LensSettingViewModel @Inject constructor(
    private val lensSettingUseCase: LensSettingUseCase
) : ViewModel() {

    private val _lensSetting: MutableState<LensSettingValue> = mutableStateOf(LensSettingValue())
    val lensSetting: State<LensSettingValue> = _lensSetting

    init {
        _lensSetting.value = lensSettingUseCase.getAllLensSetting.invoke()
    }

    fun onEvent(event: LensSettingEvent) {
        when (event) {
            is LensSettingEvent.LensType -> {
                _lensSetting.value = lensSetting.value.copy(
                    lensType = event.lensType
                )
            }
            is LensSettingEvent.LensPeriod -> {
                _lensSetting.value = lensSetting.value.copy(
                    lensPeriod = event.lensPeriod
                )
            }
            is LensSettingEvent.IsUseNotification -> {
                _lensSetting.value = lensSetting.value.copy(
                    isUseNotification = event.isUseNotification
                )
            }
            is LensSettingEvent.NotificationDay -> {
                _lensSetting.value = lensSetting.value.copy(
                    notificationDay = event.notificationType
                )
            }
            is LensSettingEvent.NotificationTimeHour -> {
                _lensSetting.value = lensSetting.value.copy(
                    notificationTimeHour = event.notificationTimeHour
                )
            }
            is LensSettingEvent.NotificationTimeMinute -> {
                _lensSetting.value = lensSetting.value.copy(
                    notificationTimeMinute = event.notificationTimeMinute
                )
            }
            is LensSettingEvent.IsShowLensPowerSection -> {
                _lensSetting.value = lensSetting.value.copy(
                    isShowLensPowerSection = event.isShowLensPowerSection
                )
            }
            is LensSettingEvent.LeftPower -> {
                _lensSetting.value = lensSetting.value.copy(
                    leftLensPower = event.leftLensPower
                )
            }
            is LensSettingEvent.RightPower -> {
                _lensSetting.value = lensSetting.value.copy(
                    rightLensPower = event.rightLensPower
                )
            }
            is LensSettingEvent.SaveLensSetting -> lensSettingUseCase.saveAllLensSetting(lensSetting.value)
        }
    }
}